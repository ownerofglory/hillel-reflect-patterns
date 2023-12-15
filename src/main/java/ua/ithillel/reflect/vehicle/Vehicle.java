package ua.ithillel.reflect.vehicle;

public abstract class Vehicle {
    private static final int DEFAULT_DISTANCE = 100;

    protected VehicleType type;

    public VehicleType getType() {
        return type;
    }

    public abstract void drive(int distance);

    public void drive() {
        drive(DEFAULT_DISTANCE);
    }
 }
