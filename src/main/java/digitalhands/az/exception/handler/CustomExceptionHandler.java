package digitalhands.az.exception.handler;

import digitalhands.az.exception.*;
import digitalhands.az.response.ExceptionResponse;
import digitalhands.az.response.ExperienceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserAlreadyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlerUserAlreadyException(UserAlreadyException exception) {
        log.error("handlerUserAlreadyException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlerIncorrectPasswordException(IncorrectPasswordException exception) {
        log.error("handlerIncorrectPasswordException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerUserNotFoundException(UserNotFoundException exception) {
        log.error("handlerUserNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(BlogPostNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handlerBlogPostNotFoundException(BlogPostNotFoundException exception) {
        log.error("handlerBlogPostNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(ContextRuntimeException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ExceptionResponse handlerContextRuntimeException(ContextRuntimeException exception) {
        log.error("handlerContextRuntimeException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.NO_CONTENT.name(), exception.getMessage());
    }

    @ExceptionHandler(ExperienceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerExperienceNotFoundException(ExperienceNotFoundException exception) {
        log.error("handlerExperienceNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(GraduateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerGraduateNotFoundException(GraduateNotFoundException exception) {
        log.error("handlerGraduateNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

}