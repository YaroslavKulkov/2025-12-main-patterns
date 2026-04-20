package ru.kulkov.api;

import ru.kulkov.geometry.Angle;

public interface Rotatable {
    Angle getAngle();
    void setAngle(Angle angle);
    Angle angularVelocity();
}
