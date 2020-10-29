package br.edu.ifsp.arq.goliveiracod.school.config.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class DefaultRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex
            , HttpHeaders headers
            , HttpStatus status
            , WebRequest request
    ) {
        List<String> errors = new ArrayList<>();

        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.add(error.getField() + ": " + error.getDefaultMessage())
        );

        ex.getBindingResult().getGlobalErrors().forEach(
                error -> errors.add(error.getObjectName() + ": " + error.getDefaultMessage())
        );

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);

        return handleExceptionInternal(
                ex, apiError, headers, apiError.getStatus(), request);
    }
}
