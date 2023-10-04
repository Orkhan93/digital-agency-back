package digitalhands.az.exception.handler;

import digitalhands.az.exception.*;
import digitalhands.az.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserAlreadyException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handlerUserAlreadyException(UserAlreadyException exception) {
        log.error("handlerUserAlreadyException {}", exception.getMessage());
        return new ExceptionResponse(BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponse handlerIncorrectPasswordException(IncorrectPasswordException exception) {
        log.error("handlerIncorrectPasswordException {}", exception.getMessage());
        return new ExceptionResponse(BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handlerUserNotFoundException(UserNotFoundException exception) {
        log.error("handlerUserNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(BlogPostNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handlerBlogPostNotFoundException(BlogPostNotFoundException exception) {
        log.error("handlerBlogPostNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(ContextRuntimeException.class)
    @ResponseStatus(NO_CONTENT)
    public ExceptionResponse handlerContextRuntimeException(ContextRuntimeException exception) {
        log.error("handlerContextRuntimeException {}", exception.getMessage());
        return new ExceptionResponse(NO_CONTENT.name(), exception.getMessage());
    }

    @ExceptionHandler(ExperienceNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handlerExperienceNotFoundException(ExperienceNotFoundException exception) {
        log.error("handlerExperienceNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(GraduateNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handlerGraduateNotFoundException(GraduateNotFoundException exception) {
        log.error("handlerGraduateNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(CollectionNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handlerCollectionException(CollectionNotFoundException exception) {
        log.error("handlerCollectionException {}", exception.getMessage());
        return new ExceptionResponse(NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handlerTeacherNotFoundException(TeacherNotFoundException exception) {
        log.error("handlerTeacherNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(UnauthorizedUserException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ExceptionResponse handlerUnauthorizedUserException(UnauthorizedUserException exception) {
        log.error("handlerUnauthorizedUserException {}", exception.getMessage());
        return new ExceptionResponse(UNAUTHORIZED.name(), exception.getMessage());
    }

}