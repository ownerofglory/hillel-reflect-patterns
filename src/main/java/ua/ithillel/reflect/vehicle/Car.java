package ua.ithillel.reflect.vehicle;

import ua.ithillel.reflect.anno.MyAnnotation;

public class Car extends Vehicle {
    @MyAnnotation
    public int seats = 5;

    public Car() {
        type = VehicleType.CAR;
    }

    @Override
    @MyAnnotation
    public void drive(int distance) {
        System.out.println("Car is driving... " + distance);
    }
}
