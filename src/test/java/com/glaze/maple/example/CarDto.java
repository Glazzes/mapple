package com.glaze.maple.example;

import java.util.Objects;

public class CarDto {
    String brand;
    int numberOfSeats;
    boolean isNew;

    public CarDto(){}

    public CarDto(String brand, int numberOfSeats, boolean isNew) {
        this.brand = brand;
        this.numberOfSeats = numberOfSeats;
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "brand='" + brand + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return numberOfSeats == carDto.numberOfSeats && isNew == carDto.isNew && Objects.equals(brand, carDto.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, numberOfSeats, isNew);
    }
}
