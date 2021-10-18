## 🍁 Mapple DTO mapper

### About
I found myself doodling around on the internet till I found this [Dynamic proxies in Java](https://www.baeldung.com/java-dynamic-proxies)
article, I always wondered how [MapStruct](https://mapstruct.org/) manages to intercept
method invocation, this way it can make use of your argument and turns it into a new class of
the return type specified by the method signature.

By the end of that short read it makes a lot of sense why the library it's implemented
through an interface and not a class, previous versions of mapstruct created a
class implementation with abstract processing, newer versions do not create 
this class implementation instead they perform all the mapping through the 
mapper interface.

### 🧰 How to build
All you need to do is to run the following command:
```
$ ./gradlew build publishMavenLocal
```
with that done, all you need to do is add the respective artifact to your favorite
build tool, in case you're using gradle an extra step to be done.
```gradle
repositories {
  mavenLocal() // do no forget to add this
  mavenCentral()
}
```
#### Artifacts
```
implementation 'com.glaze.mapple:glaze-mapple:1.0'
```
```
<dependency>
    <groupId>com.glaze.mapple</groupId>
    <artifactId>glaze-mapple</artifactId>
    <version>1.0</version>
</dependency>
```

### ✨ How to use
```java
import com.glaze.maple.annotations.Mapper;
import com.glaze.maple.annotations.Mapping;
import com.glaze.maple.core.Mappers;

@Mapper
interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "model", target = "brand")
    @Mapping(source = "seatCount", target = "numOfSeats")
    CarDto carToCarDto(Car car);
}

class Car {
    String model;
    int sitCount;
    
    constructors...
    getters and setters...
}

class CarDto {
    String brand;
    int numOfSeats;
    
    // Required no args constructor to perform instantiation and property assignment
    public constructor(){}
}
```
To the usage of this project is pretty straight forward.
- `@Mapper` annotation does nothing, but it shows intention at first glance
- You must define an INSTANCE field then call `Mappers.getMapper` and provide your
  interface type as an argument, this way a Proxy will get created the one that will
  intercept methods calls to convert the argument into your expected DTO.
- Mapping methods must follow these simple rules:
  - Must not return `void` or `Void`
  - Must not return an interface, prefer a concrete implementation
  - Must not return primitives  
  - Must take only one parameter, the given object you want to map to another
- Classes that are meant to be created dynamically (the return type of your mapping method) must
  have a no args constructor.
- `@Mapping` annotation is repeatable you can define as many as needed, it takes
  2 string parameters `source` that refers to a declared field in the object you provided
  as argument and `target` that refers to a declared field in the object that will get created dynamically of
  the specified return type.
