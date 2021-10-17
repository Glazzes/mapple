## Mapple DTO mapper

### About
I found myself doodling around on the internet till I found this [Dynamic proxies in Java](https://www.baeldung.com/java-dynamic-proxies)
article, I always wondered how [MapStruct](https://mapstruct.org/) manages to intercept
method invocations so it can make use of your argument and turn it into a new class of
the return type specified by the method signature, by the end of the short read I
realized this was my chance to create my own mapstruct clone.
### How to use
```java
import com.glaze.maple.annotations.Mapper;
import com.glaze.maple.annotations.Mapping;
import com.glaze.maple.core.Mappers;

@Mapper
interface CarMapper {
    CarMapper mapper = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "model", target = "brand")
    @Mapping(source = "seatCount", target = "numOfSeats")
    CarDto carToCarDto(Car car);
}

class Car {
    String model;
    int sitCount;
}

class CarDto {
    String brand;
    int numOfSeats;
}
```
To the usage of this project is pretty straight forward.
- `@Mapper` annotation does nothing, but it shows intention at first glance
- You must define an INSTANCE field and call `Mappers.getMapper` and provide your
instance type as an argument, this way a Proxy will get created the one that will
intercept methods calls to convert your object to your expected DTO.
- Mapping methods must take only one argument and it's return type must be 
different form `void.class` or `Void.class`
- Classes that are meant to be created dimanically (the return type of your mapping method) must
have a no args constructor.  
- `@Mapping` annotation is repeatable you can define as many as neeeded, it takes
2 string parameters `source` that refers to a class field in the argument you provided
and `target` that refers to a field in the instance that will be dinamically of
the specified return type.