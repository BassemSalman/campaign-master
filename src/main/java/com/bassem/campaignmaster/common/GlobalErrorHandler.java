package com.bassem.campaignmaster.common;

import com.bassem.campaignmaster.util.ApiResponseUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Slf4j
public class GlobalErrorHandler {
    @ExceptionHandler({EntityExistsException.class, IllegalStateException.class})
    public ResponseEntity<?> handleForbidden(Exception ex) {
        log.error(ex.getMessage());
        return ApiResponseUtil.constructResponse(ex.getMessage(),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        Map<String, String> details = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            details.put(fieldName, errorMessage);
        });

        return ApiResponseUtil.constructResponse(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class})
    public ResponseEntity<?> handleNotFound(Exception ex){
        log.error(ex.getMessage());
        return ApiResponseUtil.constructResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class, HandlerMethodValidationException.class})
    public ResponseEntity<?> handleBadRequest(Exception ex) {
        log.error(ex.getMessage());
        return ApiResponseUtil.constructResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
