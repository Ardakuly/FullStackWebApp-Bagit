package Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionAdvicer {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFound(ResourceNotFoundException resourceNotFound, WebRequest request) {
        ExceptionDetail exceptionDetail = new ExceptionDetail
                (new Date(), resourceNotFound.getMessage(), request.getDescription(true));

        return new ResponseEntity<>(exceptionDetail, HttpStatus.NOT_FOUND);
    }
}
