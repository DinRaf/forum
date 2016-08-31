package com.forum.server.controller.utils;

import com.forum.server.controller.*;
import com.forum.server.dto.response.QueryErrorDto;
import com.forum.server.dto.response.QueryResultInfoErrorDto;
import com.forum.server.security.exceptions.*;
import com.forum.server.utils.AdditionalUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 01.03.2016.
 *
 * @author Flyur Karimov (first software ingeneering platform))
 * @version 1.0
 */
@ControllerAdvice(assignableTypes = {
        MessagesController.class, RegistrationController.class,
        SearchController.class, StaticInfoController.class,
        ThemesController.class, UsersController.class
})
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({AuthException.class, IncorrectTokenException.class})
    public ResponseEntity<Object> incorrectAuthData(Exception e, WebRequest request) {
        QueryResultInfoErrorDto errorDto = createErrorDto("403", e.getMessage());
        HttpHeaders httpHeaders = AdditionalUtil.createHttpHeaders();
        return handleExceptionInternal(e, new QueryErrorDto(errorDto), httpHeaders, HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({TokenAuthenticationHeaderNotFound.class})
    public ResponseEntity<Object> incorrectToken(Exception e, WebRequest request) {
        QueryResultInfoErrorDto errorDto = createErrorDto("401", e.getMessage());
        HttpHeaders httpHeaders = AdditionalUtil.createHttpHeaders();
        return handleExceptionInternal(e, new QueryErrorDto(errorDto), httpHeaders, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> taskNotFound(Exception e, WebRequest request) {
        QueryResultInfoErrorDto errorDto = createErrorDto("404", e.getMessage());
        HttpHeaders httpHeaders = AdditionalUtil.createHttpHeaders();
        return handleExceptionInternal(e, new QueryErrorDto(errorDto), httpHeaders, HttpStatus.NOT_FOUND, request);
    }

    public QueryResultInfoErrorDto createErrorDto(String code, String message) {
        return new QueryResultInfoErrorDto(code, "fail", message);
    }

    @ExceptionHandler(RequiredFieldsAreNotFilled.class)
    public ResponseEntity<Object> methodNotAllowed(Exception e, WebRequest request) {
        QueryResultInfoErrorDto errorDto = createErrorDto("405", e.getMessage());
        HttpHeaders httpHeaders = AdditionalUtil.createHttpHeaders();
        return handleExceptionInternal(e, new QueryErrorDto(errorDto), httpHeaders, HttpStatus.METHOD_NOT_ALLOWED, request);
    }
}
