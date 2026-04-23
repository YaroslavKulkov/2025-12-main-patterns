package ru.kulkov.commands;

import ru.kulkov.api.Movable;
import ru.kulkov.entities.UObject;
import ru.kulkov.geometry.Point;
import ru.kulkov.geometry.Velocity;

public class MovingObjectAdaptor implements Movable {
    private final UObject uObject;

    public MovingObjectAdaptor(UObject uObject) {
        this.uObject = uObject;
    }

    @Override
    public Point getLocation() {
        Point location = uObject.getProperty("location", Point.class);
        if (location == null) {
            throw new IllegalStateException("Location property not found");
        }
        return location;
    }

    @Override
    public void setLocation(Point newLocation) {
        uObject.setProperty("location", newLocation);
    }

    @Override
    public Velocity getVelocity() {
        Velocity velocity = uObject.getProperty("velocity", Velocity.class);
        if (velocity == null) {
            throw new IllegalStateException("Velocity property not found");
        }
        return velocity;
    }

}
