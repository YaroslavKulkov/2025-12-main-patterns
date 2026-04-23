package ru.kulkov;

import ru.kulkov.commands.Move;
import ru.kulkov.commands.MovingObjectAdaptor;
import ru.kulkov.entities.Spaceship;
import ru.kulkov.entities.UObject;
import ru.kulkov.geometry.Angle;
import ru.kulkov.geometry.Point;
import ru.kulkov.geometry.Velocity;

public class Application {
    public static void main(String[] args) {
        UObject ship = new Spaceship(new Point(12, 5), new Velocity(-7, 3));
        ship.setProperty("velocityMagnitude", 1);
        ship.setProperty("angle", new Angle(0)); // Движение строго вправо по X

        Move moveOperation = new Move(new MovingObjectAdaptor(ship));
        moveOperation.execute();

        Point actualLocation = ship.getProperty("location", Point.class);
        System.out.println("New position: " + actualLocation.getX() + ", " + actualLocation.getY());
    }
}
