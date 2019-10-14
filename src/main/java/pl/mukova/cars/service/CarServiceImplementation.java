package pl.mukova.cars.service;

import org.springframework.stereotype.Service;
import pl.mukova.cars.model.Car;
import pl.mukova.cars.model.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImplementation implements CarService {

    private List<Car> carList;

    public CarServiceImplementation() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "Mercedes", "GLC 300", Color.NAVY_BLUE));
        carList.add(new Car(2L, "Maserati", "Quatroporte", Color.MARINE));
        carList.add(new Car(3L, "Alfa Romeo", "Giulia", Color.RED));
        carList.add(new Car(4L, "Mercedes", "EQC", Color.BLUE));
        carList.add(new Car(5L, "Ferrari", "California", Color.BLACK));
        carList.add(new Car(6L, "Bugatti", "La Voiture Noire", Color.WHITE));
    }

    @Override
    public List<Car> getAllCars() {
        return carList;
    }

    @Override
    public Optional<Car> getCarById(long id) {
        return carList.stream().filter(car -> car.getCarId() == id).findFirst();
    }

    @Override
    public List<Car> getCarsByColor(String color) {
        return carList.stream().filter(car -> color.equalsIgnoreCase(car.getColor().name()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean addCar(Car car) {
        return carList.add(car);
    }

    @Override
    public boolean updateAllFieldsForCar(Car updateCar) {
        Optional<Car> found = carList.stream().filter(car -> car.getCarId() == updateCar.getCarId()).findFirst();

        if (found.isPresent()) {
            carList.remove(found.get());
            return carList.add(updateCar);
        }
        return false;
    }

    @Override
    public boolean modifyChoiceFieldCar(long id, String brand) {
        Optional<Car> carOptional = carList.stream().filter(car -> car.getCarId() == id).findFirst();

        if (carOptional.isPresent()) {
            carOptional.get().setBrand(brand);

            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCar(long id) {
        Optional<Car> found = carList.stream().filter(car -> car.getCarId()== id).findFirst();

            if(found.isPresent()) {
                carList.remove(found.get());

                return true;
            } else {
                return false;
            }
    }
}
