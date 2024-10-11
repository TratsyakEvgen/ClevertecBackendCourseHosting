package ru.clevertec.hosting.controller;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.clevertec.hosting.dto.error.ErrorDTO;
import ru.clevertec.hosting.service.ServiceException;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorDTO processConstraintViolationException(ConstraintViolationException e) {
        log.info(e.getMessage());
        String messages = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));

        return new ErrorDTO(HttpStatus.UNPROCESSABLE_ENTITY.value(), messages);
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO processServiceException(ServiceException e) {
        log.warn(e.getMessage());
        return new ErrorDTO(HttpStatus.NOT_FOUND.value(), e.getMessage());
    }

}

