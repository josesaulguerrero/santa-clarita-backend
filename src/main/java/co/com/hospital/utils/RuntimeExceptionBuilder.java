package co.com.hospital.utils;

import java.lang.reflect.Constructor;

public class RuntimeExceptionBuilder {
    protected final Class<? extends RuntimeException> exceptionClass;
    protected String developerMessage = "No message was supplied...";

    public <K extends RuntimeException> RuntimeExceptionBuilder(Class<K> exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public RuntimeExceptionBuilder developerMessage(String message) {
        this.developerMessage = message;
        return this;
    }

    public RuntimeException build() {
        return getExceptionInstance(this.exceptionClass);
    }

    protected <K extends RuntimeException> K getExceptionInstance(Class<K> clazz) {
        try {
            Constructor<K> declaredConstructor = clazz.getDeclaredConstructor(String.class);
            return declaredConstructor.newInstance(this.developerMessage);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
