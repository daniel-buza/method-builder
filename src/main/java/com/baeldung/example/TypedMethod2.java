package com.baeldung.example;

import lombok.Builder;

import java.util.Collection;

public class TypedMethod2 {

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

    public class TypedMethodBuilder<T extends Collection> {
        public Class<T> type;
        public <U extends Collection> TypedMethodBuilder<U> type(final Class<U> type) {
            this.type = (Class)type;
            return (TypedMethodBuilder<U>)this;
        }
        public T call() {
            return typedMethod(inputFirst, inputSecond, inputThird,
                    inputFourth, inputFifth, inputSixth, type);
        }
    }

}
