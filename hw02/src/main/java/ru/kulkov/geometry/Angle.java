package ru.kulkov.geometry;

public class Angle {
    private final int degrees;

    public Angle(int degrees) {
        this.degrees = normalize(degrees);
    }

    private static int normalize(int degrees) {
        int result = degrees % 360;
        if (result < 0) {
            result += 360;
        }
        return result;
    }

    public Angle add(Angle other) {
        return new Angle(this.degrees + other.degrees);
    }

    public int getDegrees() {
        return degrees;
    }
}
