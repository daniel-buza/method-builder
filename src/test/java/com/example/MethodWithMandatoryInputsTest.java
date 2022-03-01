package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodWithMandatoryInputsTest {

    private static final MethodWithMandatoryInputs CLASS_INSTANCE = new MethodWithMandatoryInputs();

    @Test
    public void testMethodCall() {
        final String plainCallResult = CLASS_INSTANCE.method(
                "1st", "2nd", "3rd_custom"
                ,"4th", null, null);

        final String builderCallResult = CLASS_INSTANCE.mandatoryInputsMethodBuilder("1st", "2nd", "4th")
                .inputThird("3rd_custom")
                .call();

        assertEquals(plainCallResult, builderCallResult);
    }

}
