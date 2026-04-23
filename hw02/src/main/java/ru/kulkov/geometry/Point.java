package ru.kulkov.geometry;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point moveTo(Point location, Velocity velocity) {
        var newX = location.getX() + velocity.getX();
        var newY = location.getY() + velocity.getY();
        return new Point(newX, newY);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
