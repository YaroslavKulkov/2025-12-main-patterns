package ru.kulkov.commands;

import ru.kulkov.api.Rotatable;
import ru.kulkov.entities.UObject;
import ru.kulkov.geometry.Angle;

public class RotatingObjectAdaptor implements Rotatable {

    private final UObject uObject;

    public RotatingObjectAdaptor(UObject object) {
        this.uObject = object;
    }
    @Override
    public Angle getAngle() {
        Angle angle = uObject.getProperty("angle", Angle.class);
        if (angle == null) {
            throw new IllegalStateException("Angle property not found");
        }
        return angle;
    }

    @Override
    public void setAngle(Angle angle) {
        uObject.setProperty("angle", angle);
    }

    @Override
    public Angle angularVelocity() {
        Angle velocity = uObject.getProperty("angularVelocity", Angle.class);
        if (velocity == null) {
            throw new IllegalStateException("Angular velocity property not found");
        }
        return velocity;
    }
}
