package com.baeldung.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MethodWithExceptionTest {

    private static final MethodWithException CLASS_INSTANCE = new MethodWithException();

    @Test
    public void testMethodCall() {
        String plainCallResult = null;
        try {
            plainCallResult = CLASS_INSTANCE.simpleMethod(
                    null, "2", null
                    ,"4", null, "6");
        } catch (final MethodWithException.MyCustomException e) {
            e.printStackTrace();
        }

        String builderCallResult = null;
        try {
            builderCallResult = CLASS_INSTANCE.simpleMethodBuilder()
                    .inputSecond("2")
                    .inputFourth("4")
                    .inputSixth("6")
                    .call();
        } catch (final MethodWithException.MyCustomException e) {
            e.printStackTrace();
        }

        assertEquals(plainCallResult, builderCallResult);
    }

}
