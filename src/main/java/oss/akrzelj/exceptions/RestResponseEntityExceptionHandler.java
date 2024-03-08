package oss.akrzelj.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    protected ResponseEntity<Object> handleAlreadyExist(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Objects you wanna create already exists in database!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(EmailMismatchException.class)
    protected ResponseEntity<Object> handleEmailMismatch(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Sent emails doesn't match!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    protected ResponseEntity<Object> handlePasswordMismatch(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Sent passwords doesn't match!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(InvalidArgumentsException.class)
    protected ResponseEntity<Object> handleInvalidArguments(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Sent invalid or empty arguments!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ObjectDoesntExistException.class)
    protected ResponseEntity<Object> handleObjectDoesntExist(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Object you are looking for doesn't exist!";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
