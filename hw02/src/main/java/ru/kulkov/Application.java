package ru.kulkov;

import ru.kulkov.entities.Spaceship;
import ru.kulkov.commands.Move;
import ru.kulkov.commands.Rotate;

public class Application {
    public static void main(String[] args) {
        Spaceship spaceship = new Spaceship();
        Move move = new Move(spaceship);

        move.execute();
        Rotate rotate = new Rotate(spaceship);
        rotate.execute();
    }
}
