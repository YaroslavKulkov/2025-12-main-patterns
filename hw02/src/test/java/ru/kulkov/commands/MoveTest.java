package ru.kulkov.commands;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kulkov.geometry.Point;
import ru.kulkov.geometry.Velocity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MoveTest {

    @Test
    void test_PointMoveTo() {
        Point start = new Point(12, 5);
        Velocity velocity = new Velocity(-7, 3);

        Point actual = Point.moveTo(start, velocity);

        Point expected = new Point(5, 8);
        assertEquals(expected.getX(), actual.getX());
        assertEquals(expected.getY(), actual.getY());
    }
}