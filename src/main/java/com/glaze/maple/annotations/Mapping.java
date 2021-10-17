package com.glaze.maple.annotations;

import java.lang.annotation.*;

@Repeatable(Mappers.class)
public @interface Mapping {
    String source();
    String target();
}
