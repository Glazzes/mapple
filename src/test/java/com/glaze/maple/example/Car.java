package com.glaze.maple.example;

import java.util.Objects;

public class Car {
    String model;
    int sitCount;
    boolean isNew;

    public Car(){}

    public Car(String model, int sitCount, boolean isNew) {
        this.model = model;
        this.sitCount = sitCount;
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", sitCount=" + sitCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return sitCount == car.sitCount && isNew == car.isNew && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, sitCount, isNew);
    }
}
