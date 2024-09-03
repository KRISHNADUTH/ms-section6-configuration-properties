package com.eazybytes.cards.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.eazybytes.cards.dto.ErrorResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(CardAlreadyExistsException.class)
        public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistsException(CardAlreadyExistsException ex,
                        WebRequest webRequest) {
                ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                                HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
                return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex,
                        WebRequest webRequest) {
                ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                                HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
                return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(ConstraintViolationException.class)
        public ResponseEntity<ErrorResponseDto> handleConstraintViolationException(ConstraintViolationException ex,
                        WebRequest webRequest) {
                ErrorResponseDto errorResponseDto = new ErrorResponseDto(webRequest.getDescription(false),
                                HttpStatus.BAD_REQUEST, ex.getMessage(), LocalDateTime.now());
                return new ResponseEntity<ErrorResponseDto>(errorResponseDto, HttpStatus.BAD_REQUEST);
        }

        @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                Map<String, String> validationErrors = new HashMap<>();
                List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
                validationErrorList.forEach((error) -> {
                        String fieldName = ((FieldError) error).getField();
                        String validationMsg = error.getDefaultMessage();
                        validationErrors.put(fieldName, validationMsg);
                });
                return new ResponseEntity<Object>(validationErrors, HttpStatus.BAD_REQUEST);
        }

        // @ExceptionHandler(Exception.class)
        // public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex,
        // WebRequest webRequest) {
        // ErrorResponseDto errorResponseDto = new
        // ErrorResponseDto(webRequest.getDescription(false),
        // HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        // return new ResponseEntity<ErrorResponseDto>(errorResponseDto,
        // HttpStatus.INTERNAL_SERVER_ERROR);
        // }
}