package com.Company.CompanySystem.advices;

import com.Company.CompanySystem.customExceptions.ResourceNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> ResourceNotFound(ResourceNotFoundException exc){
        ApiResponse<?> errRes = ApiResponse.builder()
                .status(false)
                .message(exc.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errRes);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleInputValidationError(MethodArgumentNotValidException exc){
        List<String> errors = exc
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .toList();
//                .collect(Collectors.toList());

        System.out.println("errors" + errors);
        ApiResponse<?> errRes = ApiResponse.builder()
                .status(false)
                .message("Failure").
                entity(errors)
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errRes);

    }


//    @ExceptionHandler({
//            DataAccessException.class,
//            DataIntegrityViolationException.class
//    })
//    public ResponseEntity<ApiResponse<?>> handleDatabaseExceptions(
//            Exception exc) {
//
//        String message = "Database operation failed";
//
//        if (exc.getMessage() != null &&
//                exc.getMessage().contains("Duplicate entry")) {
//
//            message = "Record already exists";
//        }
//
//        ApiResponse<?> errResp = ApiResponse.builder()
//                .status(false)
//                .message(message)
//                .entity(null)
//                .build();
//
//        return ResponseEntity
//                .status(HttpStatus.CONFLICT)
//                .body(errResp);
//    }

    @ExceptionHandler({
            DataAccessException.class,
            DataIntegrityViolationException.class
    })
    public ResponseEntity<ApiResponse<?>> handleDatabaseExceptions(Exception exc) {

        String message = "Database operation failed";
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Throwable rootCause = exc;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }

        String rootMessage = rootCause.getMessage();

        if (rootMessage != null) {

            rootMessage = rootMessage.toLowerCase();

            if (rootMessage.contains("duplicate")
                    || rootMessage.contains("unique")) {

                message = "Record already exists";
                status = HttpStatus.CONFLICT;

            } else if (rootMessage.contains("cannot be null")
                    || rootMessage.contains("not-null")) {

                message = "Required field is missing";
                status = HttpStatus.BAD_REQUEST;

            } else if (rootMessage.contains("foreign key")) {

                message = "Referenced record does not exist";
                status = HttpStatus.BAD_REQUEST;

            } else if (rootMessage.contains("data too long")) {

                message = "Input value exceeds allowed length";
                status = HttpStatus.BAD_REQUEST;

            } else if (rootMessage.contains("constraint")) {

                message = "Database constraint violation";
                status = HttpStatus.BAD_REQUEST;
            }
        }

        ApiResponse<?> errResp = ApiResponse.builder()
                .status(false)
                .message(message)
                .entity(null)
                .build();

        return ResponseEntity
                .status(status)
                .body(errResp);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> internalServerException(Exception exc){
        ApiResponse<?> errResp = ApiResponse.builder()
                .message("failure")
                .status(false)
                .entity(exc.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errResp);
    }
}
