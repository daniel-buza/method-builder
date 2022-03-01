package com.example;

import lombok.Builder;

import java.util.List;

public class MethodWithVararg {

    @Builder(builderMethodName = "simpleMethodBuilder", buildMethodName = "call", builderClassName = "VarargMethodBuilder")
    public String simpleMethod(final String inputFirst, final String inputSecond,
                               final String inputThird, final String inputFourth,
                               final String inputFifth, final String... inputSixth) {
        return  "This method called with parameters %s %s %s %s %s %s".formatted(
                inputFirst, inputSecond, inputThird, inputFourth, inputFifth, List.of(inputSixth)
        );
    }

}
