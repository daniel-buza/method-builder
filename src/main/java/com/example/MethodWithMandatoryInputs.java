package com.example;

import lombok.Builder;

public class MethodWithMandatoryInputs {

    @Builder(builderMethodName = "mandatoryMethodBuilder", buildMethodName = "call", builderClassName = "MandatoryInputsMethodBuilder")
    public String method(final String inputFirst, final String inputSecond,
                                        final String inputThird, final String inputFourth,
                                        final String inputFifth, final String inputSixth) {
        if ((inputFirst == null) || (inputSecond == null) || (inputFourth == null)) {
            throw new RuntimeException("Mandatory input is missing!");
        }

        return "A business method called with parameters %s %s %s %s %s %s"
                .formatted(inputFirst, inputSecond, inputThird, inputFourth, inputFifth, inputSixth);
    }

    public MandatoryInputsMethodBuilder mandatoryInputsMethodBuilder(final String inputFirst, final String inputSecond, final String inputFourth) {
        return new MandatoryInputsMethodBuilder()
                .inputFirst(inputFirst)
                .inputSecond(inputSecond)
                .inputFourth(inputFourth);
    }

}
