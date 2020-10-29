package br.edu.ifsp.arq.goliveiracod.school.config.exception;

import br.edu.ifsp.arq.goliveiracod.school.exception.MentorNotFoundException;
import br.edu.ifsp.arq.goliveiracod.school.exception.ProgramNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundExceptionHandler {
    @ExceptionHandler(
            {
                    ProgramNotFoundException.class
                    , MentorNotFoundException.class
            }
    )
    public ResponseEntity<Object> handleAll(Exception ex) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), "error: " + ex.getLocalizedMessage());
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }
}
