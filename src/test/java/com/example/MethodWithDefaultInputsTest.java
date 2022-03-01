package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodWithDefaultInputsTest {

    private static final MethodWithDefaultInputs CLASS_INSTANCE = new MethodWithDefaultInputs();

    @Test
    public void testMethodCall() {
        final String plainCallResult = CLASS_INSTANCE.defaultParametersMethod(
                "1st_custom", "default_2", "3rd_custom"
                ,"default_4", "default_5", "default_6");

        final String builderCallResult = CLASS_INSTANCE.defaultParametersMethodBuilder()
                .inputThird("3rd_custom")
                .inputFirst("1st_custom")
                .call();

        assertEquals(plainCallResult, builderCallResult);
    }
}
