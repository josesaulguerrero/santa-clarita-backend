package co.com.hospital.utils;

import java.lang.reflect.Constructor;

public class RuntimeExceptionBuilder {
    private final Class<? extends RuntimeException> exceptionClass;
    private String developerMessage = "No message was supplied...";

    public<K extends RuntimeException> RuntimeExceptionBuilder(Class<K> exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public RuntimeExceptionBuilder developerMessage(String message) {
        this.developerMessage = message;
        return this;
    }

    public RuntimeException build() {
        return getExceptionInstance();
    }

    private RuntimeException getExceptionInstance() {
        try {
            Constructor<? extends RuntimeException> declaredConstructor = this.exceptionClass.getDeclaredConstructor(String.class);
            return declaredConstructor.newInstance(this.developerMessage);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
