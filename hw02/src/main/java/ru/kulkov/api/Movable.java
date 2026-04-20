package ru.kulkov.api;

import ru.kulkov.geometry.Point;
import ru.kulkov.geometry.Velocity;

public interface Movable {
    Point getLocation();
    void setLocation(Point newLocation);

    Velocity getVelocity();
}
