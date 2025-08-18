package core.basesyntax.dreamjob.exception;

import core.basesyntax.dreamjob.dto.exception.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleEntityNotFoundException(
            RuntimeException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ExceptionDto exceptionDto = new ExceptionDto(status, ex.getMessage());
        return new ResponseEntity<>(exceptionDto, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleDefaultException(
            RuntimeException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ExceptionDto exceptionDto = new ExceptionDto(status, ex.getMessage());
        return new ResponseEntity<>(exceptionDto, status);
    }
}
