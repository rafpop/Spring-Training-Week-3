package pl.mukova.cars.service;

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
boolean modifyChoiceFieldCar(long id, Car modifiedCar);
boolean deleteCar(long id);

}
