package com.glaze.maple.integration;

import com.glaze.maple.example.Car;
import com.glaze.maple.example.CarDto;
import com.glaze.maple.example.CarMapper;
import com.glaze.maple.example.IncompatibleCar;
import com.glaze.maple.expcetion.ConstructorNotFoundException;
import com.glaze.maple.expcetion.FieldNotPresentException;
import com.glaze.maple.expcetion.InvalidMethodSignatureException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    Car car;
    CarDto carDto;

    @BeforeEach
    public void setUp(){
        car = new Car("Mazda", 4, true);
        carDto = new CarDto("Mazda", 4, true);
    }

    @Test
    public void givenAValidClassShouldReturnTrue(){
        CarDto expected = CarMapper.INSTANCE.carToCarDto(car);
        assertEquals(expected, carDto);
    }

    @Test
    @DisplayName("Given a class with no args constructor as return type should thrown")
    public void shouldThrow(){
        assertThrows(ConstructorNotFoundException.class, () -> CarMapper.INSTANCE.invalidCarToCar(car));
    }

    @Test
    public void givenAMappingAnnotationWithInvalidSourceShouldThrow(){
        assertThrows(
                FieldNotPresentException.class,
                () -> CarMapper.INSTANCE.invalidSource(car)
        );
    }

    @Test
    public void givenAMappingAnnotationWithInvalidTargetShouldThrow(){
        assertThrows(
                FieldNotPresentException.class,
                () -> CarMapper.INSTANCE.invalidTarget(car)
        );
    }

    @Test
    public void givenAnInvalidSignatureShouldThrow(){
        assertThrows(
                InvalidMethodSignatureException.class,
                () -> CarMapper.INSTANCE.invalidMethodSignature(car, car)
        );
    }

    @Test
    @DisplayName("given different field types must throw")
    public void givenAnIncompatibleObjectTypesMustThrow(){
        assertThrows(
                IllegalArgumentException.class,
                () -> CarMapper.INSTANCE.incompatibleTypes(new IncompatibleCar())
        );
    }

}
