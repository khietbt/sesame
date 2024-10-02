package io.github.khietbt.shared.presentation.rest;

import io.github.khietbt.shared.domain.exceptions.ConflictException;
import io.github.khietbt.shared.domain.exceptions.ExceptionDetails;
import io.github.khietbt.shared.domain.exceptions.NotFoundException;
import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Constructor;

@ControllerAdvice
public class ControllerConfiguration {
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
}
