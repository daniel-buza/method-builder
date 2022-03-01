package com.example;

import lombok.Builder;

public class StaticMethod {

    @Builder(builderMethodName = "simpleMethodBuilder", buildMethodName = "call")
    public static String simpleMethod(final String inputFirst, final String inputSecond,
                                      final String inputThird, final String inputFourth,
                                      final String inputFifth, final String inputSixth) {
        return  "This method called with parameters %s %s %s %s %s %s".formatted(
                inputFirst, inputSecond, inputThird, inputFourth, inputFifth, inputSixth
        );
    }

}
