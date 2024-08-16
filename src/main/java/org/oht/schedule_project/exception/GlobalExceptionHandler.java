package org.oht.schedule_project.exception;

import lombok.extern.slf4j.Slf4j;
import org.oht.schedule_project.dto.response.CommonResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 특정 exception 에 대한 글로벌 처리
    @ExceptionHandler(PasswordNotSameException.class)
    public ResponseEntity<CommonResponseDto> handlePasswordMismatchException(PasswordNotSameException ex) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        return getErrorResponse(status, ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<CommonResponseDto> handleNoSuchElementException(NoSuchElementException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return getErrorResponse(status, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<CommonResponseDto> handleRuntimeException(RuntimeException ex) {

        log.info("Runtime error : " + ex.getMessage());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return getErrorResponse(status, "알수없는 오류가 발생하였습니다. 잠시 후 다시 시도해 주십시오.");
    }
    // 특정 exception 에 대한 글로벌 처리



    //
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String lastDefaultMessage = fieldErrors.get(fieldErrors.size() - 1).getDefaultMessage();

        return getErrorResponse(HttpStatus.BAD_REQUEST, lastDefaultMessage);
    }

    public ResponseEntity<CommonResponseDto> getErrorResponse(HttpStatus status, String message) {

        log.info(message);

        CommonResponseDto commonResponseDto = CommonResponseDto.builder()
                .status(status.value())
                .message(message)
                .data(null)
                .build();

        return new ResponseEntity<>(commonResponseDto, status);
    }
}