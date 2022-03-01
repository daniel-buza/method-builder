package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypedMethod2Test {

    private static final TypedMethod2 CLASS_INSTANCE = new TypedMethod2();

    @Test
    public void testMethodCall() {
        final ArrayList plainCallResult = CLASS_INSTANCE.typedMethod(
                "firstInput", null, "3rdInput",
                null, null, "SixthInput",
                ArrayList.class);

        final ArrayList builderCallResult = CLASS_INSTANCE.typedMethodBuilder()
                .type(LinkedList.class)
                .inputFirst("firstInput")
                .inputThird("3rdInput")
                .inputSixth("SixthInput")
                .type(ArrayList.class)
                .call();
        
        assertEquals(plainCallResult, builderCallResult);
    }

}
