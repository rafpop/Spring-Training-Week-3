package pl.mukova.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mukova.cars.model.Car;
import pl.mukova.cars.model.Color;
import pl.mukova.cars.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable long id) {
        Optional<Car> carById = carService.getCarById(id);

        return carById.map(car -> new ResponseEntity<>(car, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable String color) {
        List<Car> carList = carService.getCarsByColor(color);
        if (!carList.isEmpty()) {
           return new ResponseEntity<>(carList, HttpStatus.OK);
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
                                                           @RequestBody Car modifiedCar) {

        if(carService.modifyChoiceFieldCar(id, modifiedCar)) {
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
