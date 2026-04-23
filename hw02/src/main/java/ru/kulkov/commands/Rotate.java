package ru.kulkov.commands;

import ru.kulkov.api.Rotatable;
import ru.kulkov.geometry.Angle;

public class Rotate {
    private final Rotatable object;

    public Rotate(Rotatable object) {
        this.object = object;
    }

    public void execute() {
        Angle currentAngle = object.getAngle();
        Angle angularVelocity = object.angularVelocity();
        Angle newAngle = currentAngle.add(angularVelocity);
        object.setAngle(newAngle);
    }
}
