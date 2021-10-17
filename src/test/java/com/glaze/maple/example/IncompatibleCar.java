package com.glaze.maple.example;

import java.util.Objects;

public class IncompatibleCar {

    String model;
    int sitCount;
    String isNew;

    public IncompatibleCar(){}

    public IncompatibleCar(String model, int sitCount, String isNew) {
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
        if (!(o instanceof IncompatibleCar)) return false;
        IncompatibleCar that = (IncompatibleCar) o;
        return sitCount == that.sitCount && model.equals(that.model) && isNew.equals(that.isNew);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, sitCount, isNew);
    }

}
