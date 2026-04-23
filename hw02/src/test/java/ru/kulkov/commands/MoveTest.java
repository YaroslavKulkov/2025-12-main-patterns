package ru.kulkov.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kulkov.api.Movable;
import ru.kulkov.entities.UObject;
import ru.kulkov.geometry.Point;
import ru.kulkov.geometry.Velocity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MoveTest {

    @Test
    @DisplayName("Проверка корректности вычислений статическим Point.moveTo()")
    void PointMoveToShouldCorrectCalculateLocation() {
        Point start = new Point(12, 5);
        Velocity velocity = new Velocity(-7, 3);

        Point actual = Point.moveTo(start, velocity);

        Point expected = new Point(5, 8);
        assertEquals(expected.getX(), actual.getX());
        assertEquals(expected.getY(), actual.getY());
    }

    @Test
    @DisplayName("Для объекта, находящегося в точке (12, 5) и движущегося со скоростью (-7, 3) движение меняет положение объекта на (5, 8)")
    void moveShouldUpdateLocationUsingVelocity(@Mock UObject uObject) {
        Point startLocation = new Point(12, 5);
        Velocity velocity = new Velocity(-7, 3);

        when(uObject.getProperty("location", Point.class)).thenReturn(startLocation);
        when(uObject.getProperty("velocity", Velocity.class)).thenReturn(velocity);

        Movable movable = new MovingObjectAdaptor(uObject);
        Move moveCommand = new Move(movable);
        moveCommand.execute();

        ArgumentCaptor<Point> pointCaptor = ArgumentCaptor.forClass(Point.class);
        verify(uObject).setProperty(eq("location"), pointCaptor.capture());

        Point actual = pointCaptor.getValue();
        assertEquals(5, actual.getX());
        assertEquals(8, actual.getY());
    }

    @Test
    @DisplayName("Попытка сдвинуть объект, у которого невозможно прочитать положение в пространстве, приводит к ошибке")
    void moveShouldThrowExceptionWhenLocationNotReadable(@Mock UObject uObject) {
        when(uObject.getProperty("location", Point.class))
                .thenThrow(new IllegalArgumentException("Location property not found"));

        Movable movable = new MovingObjectAdaptor(uObject);
        Move moveCommand = new Move(movable);

        // when & then
        assertThrows(IllegalArgumentException.class, moveCommand::execute);
    }
}