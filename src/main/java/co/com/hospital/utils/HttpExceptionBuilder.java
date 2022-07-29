package co.com.hospital.utils;

import org.springframework.http.HttpStatus;

import java.lang.reflect.Constructor;

public class HttpExceptionBuilder extends RuntimeExceptionBuilder {
    private HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;

    public HttpExceptionBuilder() {
        super(HttpException.class);
    }

    @Override
    public HttpExceptionBuilder developerMessage(String message) {
        this.developerMessage = message;
        return this;
    }

    public HttpExceptionBuilder statusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    @Override
    public HttpException build() {
        return this.getExceptionInstance(HttpException.class);
    }

    @Override
    protected <K extends RuntimeException> K getExceptionInstance(Class<K> clazz) {
        try {
            Constructor<K> declaredConstructor = clazz.getDeclaredConstructor(String.class, HttpStatus.class);
            return declaredConstructor.newInstance(this.developerMessage, this.statusCode);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
