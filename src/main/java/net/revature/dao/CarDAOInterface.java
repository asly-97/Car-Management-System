package net.revature.dao;

import net.revature.model.Car;
import net.revature.model.Person;

import java.util.List;

public interface CarDAOInterface {

    List<Car> getAllCars();
    Car getCarById(int id);
    List<Car> getCarsByOwner(Person owner);
    Car updateCar(Car car);
    Car addCar(Car car);
    void deleteCar(int car_id);
    Car assignOwner(Car car, Person owner);




}
