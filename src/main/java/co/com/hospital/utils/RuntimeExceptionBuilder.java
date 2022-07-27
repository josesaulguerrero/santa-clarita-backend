package co.com.hospital.utils;

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
            return this.exceptionClass.getDeclaredConstructor(String.class).newInstance(this.developerMessage);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
