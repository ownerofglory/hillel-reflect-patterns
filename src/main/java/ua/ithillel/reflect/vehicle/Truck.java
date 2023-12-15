package ua.ithillel.reflect.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.ithillel.reflect.anno.MyAnnotation;

public class Truck extends Vehicle {
    @JsonProperty("seatCount")
    private final int seats = 5;
    public Truck() {
        type = VehicleType.TRUCK;
    }

    @Override
    @MyAnnotation(retryCount = 4)
    public void drive(int distance) {
        System.out.println("Truck is driving... " + distance);
    }

    private boolean check() {
        return false;
    }
}
