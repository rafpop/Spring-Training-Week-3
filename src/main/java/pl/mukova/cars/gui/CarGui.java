package pl.mukova.cars.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.mukova.cars.model.Car;
import pl.mukova.cars.service.CarService;

import java.util.Optional;

@Controller
public class CarGui {

    private CarService carService;

    @Autowired
    public CarGui(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars-web")
    public String getCars(Model model) {
        model.addAttribute("carList", carService.getAllCars());
        model.addAttribute("readId", carService.getListId());
        model.addAttribute("addCar", new Car());
        model.addAttribute("color", carService.getAllCarColors());
        model.addAttribute("id", "");
        model.addAttribute("modifyCar", new Car());
        return "cars-web";
    }

    @GetMapping(value = "/cars-web/findCarById", params = "id")
    public String getCarsById(@RequestParam long id, Model model) {
        Optional<Car> foundCar = carService.getCarById(id);
        model.addAttribute("foundCar", foundCar);
        return "findCarById";
    }

    @PostMapping("/cars-web/addCar")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars-web";
    }

    @PostMapping("/cars-web/modifyCar")
    public String modifyCar(@ModelAttribute Car updateCar) {
        carService.updateAllFieldsForCar(updateCar);
        return "cars-web";
    }

    @GetMapping(value = "/cars-web/deleteCar", params = "id")
    public String deleteCar(@RequestParam long id) {
        carService.deleteCar(id);
        return "redirect:/cars-web";
    }

}
