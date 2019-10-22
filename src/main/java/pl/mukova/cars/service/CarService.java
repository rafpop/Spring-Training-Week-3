package pl.mukova.cars.service;

import org.springframework.stereotype.Service;
import pl.mukova.cars.model.Car;
import pl.mukova.cars.model.Color;

import java.util.List;
import java.util.Optional;

public interface CarService {

List<Car> getAllCars();
Optional<Car> getCarById(long id);
List<Car> getCarsByColor(String color);
boolean addCar(Car car);
boolean updateAllFieldsForCar (Car updateCar);
boolean modifyChoiceFieldCar(long id, String brand);
boolean deleteCar(long id);
List<Long> getListId();
List<Color> getAllCarColors();
long setNewIdForCar();

}
