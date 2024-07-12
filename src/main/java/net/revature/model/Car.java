package net.revature.model;

public class Car {
    private int id;
    private String make;
    private String model;
    private byte passengers;
    private String color;
    private Person owner;

    public Car(int id, String make, String model, byte passengers, String color) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.passengers = passengers;
        this.color = color;
    }

    public Car(int id, String make, String model, byte passengers, String color, Person owner) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.passengers = passengers;
        this.color = color;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public byte getPassengers() {
        return passengers;
    }

    public String getColor() {
        return color;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
