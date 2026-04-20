package ru.kulkov.entities;

import ru.kulkov.api.Movable;
import ru.kulkov.api.Rotatable;
import ru.kulkov.geometry.Angle;
import ru.kulkov.geometry.Point;
import ru.kulkov.geometry.Velocity;

public class Spaceship implements Movable, Rotatable {
    private Point location;
    private Velocity velocity;
    private Angle angle;

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point newPosition) {
        this.location = newPosition;
    }

    @Override
    public Velocity getVelocity() {
        return velocity;
    }

    @Override
    public Angle getAngle() {
        return angle;
    }

    @Override
    public void setAngle(Angle angle) {
        this.angle = angle;
    }

    @Override
    public Angle angularVelocity() {
        return null;
    }
}
