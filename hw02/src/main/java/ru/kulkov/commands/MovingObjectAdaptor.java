package ru.kulkov.commands;

import ru.kulkov.api.Movable;
import ru.kulkov.entities.UObject;
import ru.kulkov.geometry.Angle;
import ru.kulkov.geometry.Point;
import ru.kulkov.geometry.Velocity;

public class MovingObjectAdaptor implements Movable {
    private final UObject uObject;
    public MovingObjectAdaptor(UObject uObject) {
        this.uObject = uObject;
    }
    @Override
    public Point getLocation() {
        //будем хранить модуль скорости и угловую скорость
        //Point location = (Point) uObject.getProperty("location");
        Point location = uObject.getProperty("location", Point.class);
        Integer velocity = uObject.getProperty("velocity", Integer.class);
        Angle angle = uObject.getProperty("angle", Angle.class);

        Point point = Point.moveTo(location, new Velocity(velocity * Math.cos(angle), velocity * Math.sin(angle)));
        uObject.setProperty("location", point);

        return location;
    }

    @Override
    public void setLocation(Point newLocation) {

    }

    @Override
    public Velocity getVelocity() {
        return null;
    }
}
