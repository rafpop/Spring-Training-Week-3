package pl.mukova.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mukova.cars.model.Car;
import pl.mukova.cars.service.CarService;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<Resources<Car>> getAllCars() {
        List<Car> carList = carService.getAllCars();
        carList.forEach(car -> car.add(linkTo(CarController.class).slash(car.getCarId()).withSelfRel()));
        Link link = linkTo(CarController.class).withSelfRel();
        Resources<Car> carResources = new Resources<>(carList, link);
        return new ResponseEntity<>(carResources, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource<Car>> getCarById(@PathVariable Long id) {
        Optional<Car> carById = carService.getCarById(id);
        Link link = linkTo(CarController.class).slash(id).withSelfRel();
        Resource<Car> carResource = new Resource<>(carById.get(), link);
        return carById.map(car -> new ResponseEntity<>(carResource, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<Resources<Car>> getCarByColor(@PathVariable String color) {
        List<Car> carList = carService.getCarsByColor(color);
        carList.forEach(car -> car.add(linkTo(CarController.class).withRel("allColors")));
        Link link = linkTo(CarController.class).withSelfRel();
        Resources<Car> carResources = new Resources<>(carList, link);
        if (!carList.isEmpty()) {
           return new ResponseEntity<>(carResources, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {

        if(carService.addCar(car)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity<Car> updateCar(@RequestBody Car car) {
        if(carService.updateAllFieldsForCar(car)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    public ResponseEntity updateSomeFieldsInChoiceCar(@PathVariable long id,
                                                      @RequestParam String brand) {

        if(carService.modifyChoiceFieldCar(id, brand)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable long id) {
        if(carService.deleteCar(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
