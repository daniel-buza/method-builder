package com.baeldung.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodWithVarargTest {

    private static final MethodWithVararg CLASS_INSTANCE = new MethodWithVararg();

    @Test
    public void testMethodCall() {
        final String plainCallResult = CLASS_INSTANCE.simpleMethod(
                "firstInput", null, "3rdInput",
                null, null, "SixthInput_1", "SixthInput_2");
        final String builderCallResult = CLASS_INSTANCE.simpleMethodBuilder()
                .inputFirst("firstInput")
                .inputThird("3rdInput")
                .inputSixth(new String[]{"SixthInput_1", "SixthInput_2"})
                .call();
        assertEquals(plainCallResult, builderCallResult);
    }

}
