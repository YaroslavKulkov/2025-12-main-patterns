package ru.kulkov.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kulkov.api.Rotatable;
import ru.kulkov.entities.UObject;
import ru.kulkov.geometry.Angle;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RotateTest {

    @Test
    @DisplayName("Проверка корректности вращения объекта")
    void rotateShouldChangeAngleCorrectly(@Mock UObject uObject) {
        Angle initialAngle = new Angle(90);
        Angle angularVelocity = new Angle(45);
        int expectedAngle = 135;

        when(uObject.getProperty("angle", Angle.class)).thenReturn(initialAngle);
        when(uObject.getProperty("angularVelocity", Angle.class)).thenReturn(angularVelocity);

        Rotatable rotatable = new RotatingObjectAdaptor(uObject);
        Rotate rotateCommand = new Rotate(rotatable);
        rotateCommand.execute();

        ArgumentCaptor<Angle> pointCaptor = ArgumentCaptor.forClass(Angle.class);
        verify(uObject).setProperty(eq("angle"), pointCaptor.capture());

        Angle actual = pointCaptor.getValue();
        assertEquals(expectedAngle, actual.getDegrees());
    }

    @Test
    @DisplayName("Проверка нормализации угла при превышении 360 градусов")
    void rotateShouldNormalizeAngleWhenExceeds360(@Mock UObject uObject) {
        Angle initialAngle = new Angle(350);
        Angle angularVelocity = new Angle(20);
        int expectedAngle = 10; // 370 -> 10

        when(uObject.getProperty("angle", Angle.class)).thenReturn(initialAngle);
        when(uObject.getProperty("angularVelocity", Angle.class)).thenReturn(angularVelocity);

        Rotatable rotatable = new RotatingObjectAdaptor(uObject);
        Rotate rotateCommand = new Rotate(rotatable);
        rotateCommand.execute();

        ArgumentCaptor<Angle> pointCaptor = ArgumentCaptor.forClass(Angle.class);
        verify(uObject).setProperty(eq("angle"), pointCaptor.capture());

        Angle actual = pointCaptor.getValue();
        assertEquals(expectedAngle, actual.getDegrees());
    }

    @Test
    @DisplayName("Попытка повернуть объект, у которого невозможно прочитать значение угла, приводит к ошибке")
    void rotateShouldThrowExceptionWhenAngleNotReadable(@Mock UObject uObject) {
        when(uObject.getProperty("angle", Angle.class)).thenReturn(null);

        Rotatable rotatable = new RotatingObjectAdaptor(uObject);
        Rotate rotateCommand = new Rotate(rotatable);

        assertThrows(IllegalStateException.class, rotateCommand::execute);
        verify(uObject, never()).setProperty(eq("angle"), any());
    }

    @Test
    @DisplayName("Попытка повернуть объект, у которого невозможно прочитать угловую скорость, приводит к ошибке")
    void rotateShouldThrowExceptionWhenAngularVelocityNotReadable(@Mock UObject uObject) {
        when(uObject.getProperty("angle", Angle.class)).thenReturn(new Angle(90));
        when(uObject.getProperty("angularVelocity", Angle.class)).thenReturn(null);

        Rotatable rotatable = new RotatingObjectAdaptor(uObject);
        Rotate rotateCommand = new Rotate(rotatable);

        assertThrows(IllegalStateException.class, rotateCommand::execute);
        verify(uObject, never()).setProperty(eq("angle"), any());
    }

    @Test
    @DisplayName("Попытка повернуть объект, у которого невозможно изменить положение в пространстве, приводит к ошибке")
    void rotateShouldThrowExceptionWhenAngleCannotBeChanged(@Mock UObject uObject) {
        Angle initialAngle = new Angle(90);
        Angle angularVelocity = new Angle(45);

        when(uObject.getProperty("angle", Angle.class)).thenReturn(initialAngle);
        when(uObject.getProperty("angularVelocity", Angle.class)).thenReturn(angularVelocity);

        doThrow(new UnsupportedOperationException("Cannot set angle on read-only object"))
                .when(uObject).setProperty(eq("angle"), any(Angle.class));

        Rotatable rotatable = new RotatingObjectAdaptor(uObject);
        Rotate rotateCommand = new Rotate(rotatable);

        assertThrows(UnsupportedOperationException.class, rotateCommand::execute);
    }

}