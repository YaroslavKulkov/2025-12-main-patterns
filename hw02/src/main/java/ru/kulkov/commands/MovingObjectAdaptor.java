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
        return uObject.getProperty("location", Point.class);
    }

    @Override
    public void setLocation(Point newLocation) {
        uObject.setProperty("location", newLocation);
    }

    @Override
    public Velocity getVelocity() {
        return uObject.getProperty("velocity", Velocity.class);
    }

}
