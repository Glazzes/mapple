package com.glaze.maple.example;

import com.glaze.maple.annotations.Mapper;
import com.glaze.maple.annotations.Mapping;
import com.glaze.maple.core.Mappers;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "sitCount", target = "numberOfSeats")
    @Mapping(source = "model", target = "brand")
    @Mapping(source = "isNew", target = "isNew")
    CarDto carToCarDto(Car car);

    InvalidCar invalidCarToCar(Car car);

    @Mapping(source = "RANDOM", target = "numberOfSeats")
    @Mapping(source = "RANDOM", target = "brand")
    @Mapping(source = "RANDOM", target = "isNew")
    CarDto invalidSource(Car car);

    @Mapping(source = "sitCount", target = "RANDOM")
    @Mapping(source = "model", target = "RANDOM")
    @Mapping(source = "isNew", target = "RANDOM")
    CarDto invalidTarget(Car car);

    CarDto invalidMethodSignature(Car car, Car car2);

    @Mapping(source = "sitCount", target = "numberOfSeats")
    @Mapping(source = "model", target = "brand")
    @Mapping(source = "isNew", target = "isNew")
    CarDto incompatibleTypes(IncompatibleCar car);

}