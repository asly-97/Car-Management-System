package net.revature.controller;

import io.javalin.http.Handler;
import net.revature.exception.car.CarNotFoundException;
import net.revature.exception.person.PersonNotFoundException;
import net.revature.model.Car;
import net.revature.model.Person;
import net.revature.service.CarService;

import java.util.List;

public class CarController {

    CarService carService = new CarService();

    public Handler getAllCarsHandler= ctx -> {

        List<Car> cars = carService.getAllCars();

        ctx.status(201);
        ctx.json(cars);

    };

    public Handler getCarById = ctx -> {
        int carId = Integer.parseInt(ctx.pathParam("id"));

        try {
            Car car = carService.getCarById(carId);
            ctx.status(201);
            ctx.json(car);
        }
        catch (CarNotFoundException e){
            ctx.status(404);
            ctx.json(e.getMessage());
        }

    };

    public Handler getCarsByOwnerId = ctx -> {
        int ownerId = Integer.parseInt(ctx.pathParam("id"));

        try {
            List<Car> carList = carService.getCarsByOwner(ownerId);
            ctx.status(201);
            ctx.json(carList);
        }
        catch (PersonNotFoundException e){
            ctx.status(404);
            ctx.json(e.getMessage());
        }

    };

    public Handler assignCarOwnerHandler = ctx -> {
        int carId = Integer.parseInt(ctx.pathParam("car_id"));
        int ownerId = Integer.parseInt(ctx.pathParam("person_id"));

        try {
            Car car = carService.assignOwner(carId,ownerId);
            ctx.status(201);
            ctx.json(car);
        }
        catch (CarNotFoundException | PersonNotFoundException e){
            ctx.status(404);
            ctx.json(e.getMessage());
        }

    };

    public Handler addNewCarHandler= ctx -> {

        Car newCar = ctx.bodyAsClass(Car.class);

        Car addedCar = carService.addCar(newCar);

        ctx.status(201);
        ctx.json(addedCar);

    };

    public Handler deleteCarController= ctx -> {

        int car_id = Integer.parseInt(ctx.pathParam("id"));

        carService.deleteCar(car_id);

        ctx.status(201);

    };

}
