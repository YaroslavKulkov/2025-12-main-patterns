package ru.kulkov.entities;

public interface UObject {

    <T> T getProperty(String key, Class<T> propertyType);
    //Object getProperty(String key);

    void setProperty(String key, Object value);

}
