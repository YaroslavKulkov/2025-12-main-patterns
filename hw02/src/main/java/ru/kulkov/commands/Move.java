package ru.kulkov.commands;

import ru.kulkov.api.Movable;
import ru.kulkov.geometry.Point;

public class Move {
    private final Movable object;

    public Move(Movable object) {
        this.object = object;
    }

    public void execute() {
        object.setLocation(
                Point.moveTo(object.getLocation(), object.getVelocity())
        );
    }
}
