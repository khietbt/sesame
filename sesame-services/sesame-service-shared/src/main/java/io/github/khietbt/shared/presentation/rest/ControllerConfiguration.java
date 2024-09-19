package io.github.khietbt.shared.presentation.rest;

import io.github.khietbt.shared.domain.exceptions.ConflictException;
import io.github.khietbt.shared.domain.exceptions.ExceptionDetails;
import io.github.khietbt.shared.domain.exceptions.NotFoundException;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Constructor;

@ControllerAdvice
public class ControllerConfiguration implements ResponseBodyAdvice<Object> {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handle(RuntimeException e) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;

        var throwable = this.getCause(e);

        if (throwable instanceof ConflictException) {
            status = HttpStatus.CONFLICT;
        } else if (throwable instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status).body(
                WrapperResponse.error(throwable.getMessage())
        );
    }

    private Throwable getCause(RuntimeException e) {
        if (e instanceof CommandExecutionException ex) {
            return ex.<ExceptionDetails>getDetails()
                    .map(
                            details -> {
                                try {
                                    String className = details.getName();
                                    String message = details.getMessage();

                                    Class<?> clazz = Class.forName(className);
                                    Constructor<?> constructor = clazz.getConstructor(String.class);

                                    return (Throwable) constructor.newInstance(message);
                                } catch (Exception ignored) {
                                    return e;
                                }
                            }
                    )
                    .orElse(e);
        }

        return e;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object original,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response
    ) {
        if (!(original instanceof WrapperResponse)) {
            return WrapperResponse.success(original);
        }

        return original;
    }
}
