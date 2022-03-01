package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypedMethod1Test {

    private static final TypedMethod1 CLASS_INSTANCE = new TypedMethod1();

    @Test
    public void testMethodCall() {
        final ArrayList plainCallResult = CLASS_INSTANCE.typedMethod(
                "firstInput", null, "3rdInput",
                null, null, "SixthInput",
                ArrayList.class);

        final ArrayList builderCallResult = CLASS_INSTANCE.typedMethodBuilder(ArrayList.class)
                .inputFirst("firstInput")
                .inputThird("3rdInput")
                .inputSixth("SixthInput")
                .call();

        assertEquals(plainCallResult, builderCallResult);
    }

}
