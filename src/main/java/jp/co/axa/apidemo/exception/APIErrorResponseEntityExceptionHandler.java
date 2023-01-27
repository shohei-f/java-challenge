package jp.co.axa.apidemo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jp.co.axa.apidemo.controllers.EmployeeNotFoundException;

/**
 * Handle API Errors
 * @author shohei
 */
@ControllerAdvice
public class APIErrorResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * All Exceptions
     * @param ex exception
     * @param request request details
     * @return error response
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        APIErrorDetails details = initAPIErrorDetails (ex, request);
        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * EmployeeNotFoundException
     * @param ex exception
     * @param request request details
     * @return error response
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public final ResponseEntity<Object> handleEmployeeNotFoundException(Exception ex, WebRequest request) {
        APIErrorDetails details = initAPIErrorDetails (ex, request);
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        APIErrorDetails details = initAPIErrorDetails (ex, request);
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    /**
     * Initialize APIErrorDetails
     * @param ex exception
     * @param request request details
     * @return Initialized APIErrorDetails
     */
    private APIErrorDetails initAPIErrorDetails(Exception ex, WebRequest request) {
        return new APIErrorDetails(
                ex.getMessage(),                 // message
                request.getDescription(false),   // URI
                LocalDateTime.now()              // timestamp
                );
    }
}
