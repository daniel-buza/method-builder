package com.example;

import lombok.Builder;

public class MethodWithDefaultInputs {

    @Builder(builderMethodName = "defaultParametersMethodBuilder",  buildMethodName = "call", builderClassName = "DefaultParametersMethodBuilder")
    public String defaultParametersMethod(final String inputFirst, final String inputSecond,
                                          final String inputThird, final String inputFourth,
                                          final String inputFifth, final String inputSixth) {
        return  "A method - with a builder which knows about default values - called with parameters %s %s %s %s %s %s".formatted(
                inputFirst, inputSecond, inputThird, inputFourth, inputFifth, inputSixth
        );
    }

    public class DefaultParametersMethodBuilder {
        private String inputFirst = "default_1";
        private String inputSecond = "default_2";
        private String inputThird = "default_3";
        private String inputFourth = "default_4";
        private String inputFifth = "default_5";
        private String inputSixth = "default_6";
    }

}
