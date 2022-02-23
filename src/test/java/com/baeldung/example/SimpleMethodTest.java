package com.baeldung.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleMethodTest {

    private static final SimpleMethod CLASS_INSTANCE = new SimpleMethod();

    @Test
    public void testMethodCall() {
        final String plainCallResult = CLASS_INSTANCE.simpleMethod(
                "firstInput", null, "3rdInput",
                null, null, "SixthInput");
        final String builderCallResult = CLASS_INSTANCE.simpleMethodBuilder()
                .inputFirst("firstInput")
                .inputThird("3rdInput")
                .inputSixth("SixthInput")
                .call();
        assertEquals(plainCallResult, builderCallResult);
    }

}
