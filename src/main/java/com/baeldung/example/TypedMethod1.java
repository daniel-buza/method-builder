package com.baeldung.example;

import lombok.Builder;

import java.util.Collection;

public class TypedMethod1 {

    @Builder(builderMethodName = "typedMethodBuilder", buildMethodName = "call", builderClassName = "TypedMethodBuilder")
    public <T extends Collection> T typedMethod(final String inputFirst, final String inputSecond,
                                                       final String inputThird, final String inputFourth,
                                                       final String inputFifth, final String inputSixth,
                                                       final Class<T> type) {
        try {
            final T result = type.getDeclaredConstructor().newInstance();
            result.add(inputFirst);
            result.add(inputSecond);
            result.add(inputThird);
            result.add(inputFourth);
            result.add(inputFifth);
            result.add(inputSixth);
            return result;
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Collection> TypedMethodBuilder<T> typedMethodBuilder(final Class<T> type) {
        return new TypedMethodBuilder<T>().type(type);
    }

    public class TypedMethodBuilder<T extends Collection> {
        private Class<T> type;
        public TypedMethodBuilder<T> type(final Class<T> type) { this.type = type; return this; }
        public T call() {
            return typedMethod(inputFirst, inputSecond, inputThird,
                    inputFourth, inputFifth, inputSixth, type);
        }
    }

}
