package com.baeldung.example;

import lombok.Builder;

public class MethodWithException {

    public static class MyCustomException extends Exception {}

    @Builder(builderMethodName = "simpleMethodBuilder", buildMethodName = "call")
    public String simpleMethod(final String inputFirst, final String inputSecond,
                               final String inputThird, final String inputFourth,
                               final String inputFifth, final String inputSixth) throws MyCustomException {
        return  "This method called with parameters %s %s %s %s %s %s".formatted(
                inputFirst, inputSecond, inputThird, inputFourth, inputFifth, inputSixth
        );
    }
}
