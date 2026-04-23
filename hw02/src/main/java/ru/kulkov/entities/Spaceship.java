package ru.kulkov.entities;

import ru.kulkov.geometry.Point;
import ru.kulkov.geometry.Velocity;

import java.util.HashMap;
import java.util.Map;

public class Spaceship implements UObject {
    private final Map<String, Object> properties = new HashMap<>();

    public Spaceship(Point location, Velocity velocity) {
        properties.put("location", location);
        properties.put("velocity", velocity);
    }

    @Override
    public <T> T getProperty(String key, Class<T> propertyType) {
        Object value = properties.get(key);
        if (value == null) {
            throw new IllegalArgumentException("Property '" + key + "' not found in UObject");
        }
        return propertyType.cast(value);
    }

    @Override
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }
}
