package ru.kulkov.geometry;

public class Angle {
    private final int degrees;

    public Angle(int degrees) {
        this.degrees = degrees % 360;
    }

    public double getRadians() {
        return Math.toRadians(degrees);
    }
}
