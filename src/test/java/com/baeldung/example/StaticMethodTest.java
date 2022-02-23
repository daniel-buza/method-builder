package com.baeldung.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaticMethodTest {
    @Test
    public void testMethodCall() {
        final String plainCallResult = StaticMethod.simpleMethod(
                "firstInput", null, "3rdInput",
                null, null, "SixthInput");

        final String builderCallResult = StaticMethod.simpleMethodBuilder()
                .inputFirst("firstInput")
                .inputThird("3rdInput")
                .inputSixth("SixthInput")
                .call();

        assertEquals(plainCallResult, builderCallResult);
    }

}
