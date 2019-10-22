package pl.mukova.cars.service;

import org.springframework.stereotype.Service;
import pl.mukova.cars.model.Car;
import pl.mukova.cars.model.Color;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarServiceImplementation implements CarService {

    private List<Car> carList;
    private List<Long> idList;
    private List<Color> colorList;

    public CarServiceImplementation() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "Mercedes", "GLC 300", Color.NAVY_BLUE));
        carList.add(new Car(2L, "Maserati", "Quatroporte", Color.MARINE));
        carList.add(new Car(3L, "Alfa Romeo", "Giulia", Color.RED));
        carList.add(new Car(4L, "Mercedes", "EQC", Color.BLUE));
        carList.add(new Car(5L, "Ferrari", "California", Color.BLACK));
        carList.add(new Car(6L, "Bugatti", "La Voiture Noire", Color.WHITE));
        this.idList = new ArrayList<>();
        this.colorList = Arrays.asList(Color.values());
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
    public boolean addCar(Car newCar) {

        if(newCar != null) {
            long id = setNewIdForCar();
            newCar.setCarId(id);
            return carList.add(newCar);
        }
        return false;
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

        return carList.remove(found.get());
    }

    @Override
    public List<Long> getListId() {
        idList.removeAll(idList);

        for (Car car: carList) {
            idList.add(car.getCarId());
        }
        return idList;
    }

    @Override
    public List<Color> getAllCarColors() {
        return colorList;
    }

    @Override
    public long setNewIdForCar() {
        List<Long> idListForSet = carList.stream().map(Car::getCarId).sorted().collect(Collectors.toList());

        if (idListForSet.size() > 0) {
            return idListForSet.get(idListForSet.size() -1) + 1;
        }
        return 0;
    }

}
