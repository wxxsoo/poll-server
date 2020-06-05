package com.example.poll.advice;

import com.example.poll.exception.NotFoundException;
import com.example.poll.response.ApiException;
import com.example.poll.response.ApiResponseCode;
import com.example.poll.response.ApiResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Objects;

@Slf4j
@Order(value = 1)
@RestControllerAdvice
public class ApiCommonAdvice {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler
    public ApiResponseDto<String> handleBaseException(ApiException e) {
        return ApiResponseDto.createException(e);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({ ConstraintViolationException.class })
    public ApiResponseDto<String> handleBaseException(ConstraintViolationException e) {
        return ApiResponseDto.createException(ApiResponseCode.BAD_PARAMETER, "파라미터가 잘못됐습니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ NotFoundException.class })
    public ApiResponseDto<String> handleValidException(NotFoundException e) {
        ApiResponseDto<String> exception = ApiResponseDto
                .createException(new ApiException(ApiResponseCode.NOT_FOUND, e.getMessage()));
        log.error("[{}] {}", ApiResponseCode.NOT_FOUND.getKey(), exception);
        return exception;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ ConversionFailedException.class })
    public ApiResponseDto<String> handleValidException(ConversionFailedException e) {
        String errorMessage = String.format(" %s 요청 파라미터를 확인해주세요.", e.getTargetType().getType().getSimpleName());
        ApiResponseDto<String> exception = ApiResponseDto
                .createException(new ApiException(ApiResponseCode.BAD_PARAMETER, errorMessage));
        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getKey(), exception);
        return exception;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ BindException.class })
    public ApiResponseDto<ErrorMessageCollection> handleValidException(BindException e) {

        ApiResponseDto<ErrorMessageCollection> exception = ApiResponseDto.createException(ApiResponseCode.BAD_PARAMETER,
                new ErrorMessageCollection(e.getBindingResult().getFieldErrors(),
                        e.getBindingResult().getGlobalErrors()));

        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getKey(), exception);
        return exception;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ApiResponseDto<String> handleValidException(MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();

        String errorMessage = String.format(" %s 요청 파라미터를 확인해주세요.",
                Objects.requireNonNull(result.getFieldError()).getField());
        ApiResponseDto<String> exception = ApiResponseDto
                .createException(new ApiException(ApiResponseCode.BAD_PARAMETER, errorMessage));
        log.error("[{}] {}", ApiResponseCode.BAD_PARAMETER.getKey(), exception);
        return exception;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ NumberFormatException.class })
    public ApiResponseDto<String> handleValidException(NumberFormatException e) {

        return ApiResponseDto.createException(ApiResponseCode.BAD_PARAMETER, "파라미터가 잘못됐습니다.");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ org.springframework.http.converter.HttpMessageNotReadableException.class })
    public ApiResponseDto<String> handleValidException(HttpMessageNotReadableException e) {
        return ApiResponseDto.createException(ApiResponseCode.BAD_PARAMETER, "입력 값이 없습니다.");
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiResponseDto<String> AccessDeniedException(AccessDeniedException e) {
        return ApiResponseDto.createException(ApiResponseCode.ACCESS_DENIED, ApiResponseCode.ACCESS_DENIED.getValue());
    }
}
