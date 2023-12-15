package ua.ithillel.reflect.vehicle;

public class VehicleFactory {
    public Vehicle createVehicle(VehicleType type) {
        switch (type) {
            case CAR -> new Car();
            case TRUCK -> new Truck();
            default -> throw new RuntimeException();
        }

        throw new RuntimeException();
    }
}
