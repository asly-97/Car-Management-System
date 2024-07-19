package net.revature.exception.car;

public class CarNotFoundException extends CarException{
    public CarNotFoundException(int car_id) {
        super("Car with id("+car_id+") was not found");
    }
}
