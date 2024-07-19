package net.revature.service;

import net.revature.dao.CarDAO;
import net.revature.dao.CarDAOInterface;
import net.revature.exception.car.CarNotFoundException;
import net.revature.exception.person.PersonNotFoundException;
import net.revature.model.Car;
import net.revature.model.Person;

import java.util.List;

public class CarService {
    private CarDAOInterface carDAO = new CarDAO();
    private PersonService personService = new PersonService();

    public List<Car> getAllCars(){
        return carDAO.getAllCars();
    }

    public Car getCarById(int id) throws CarNotFoundException{
        Car car = carDAO.getCarById(id);
        if(car == null){
            throw new CarNotFoundException(id);
        }
        return car;
    }

    public List<Car> getCarsByOwner(int ownerId) throws PersonNotFoundException {
        Person owner = personService.getPersonById(ownerId);
        return carDAO.getCarsByOwner(owner);
    }

    public Car assignOwner(int car_id,int person_id) throws CarNotFoundException, PersonNotFoundException {
        Car car = getCarById(car_id);
        Person person = personService.getPersonById(person_id);

        return carDAO.assignOwner(car,person);
    }

    public Car addCar(Car newCar){
        Car addedCar = carDAO.addCar(newCar);

        return addedCar;
    }

    public void deleteCar(int car_id){
        carDAO.deleteCar(car_id);
    }


}
