package digitalhands.az.exception.handler;

import digitalhands.az.exception.*;
import digitalhands.az.response.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserAlreadyException.class)
    public ExceptionResponse handlerUserAlreadyException(UserAlreadyException exception) {
        log.error("handlerUserAlreadyException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ExceptionResponse handlerIncorrectPasswordException(IncorrectPasswordException exception) {
        log.error("handlerIncorrectPasswordException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponse handlerUserNotFoundException(UserNotFoundException exception) {
        log.error("handlerUserNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

    @ExceptionHandler(BlogPostNotFoundException.class)
    public ExceptionResponse handlerBlogPostNotFoundException(BlogPostNotFoundException exception) {
        log.error("handlerBlogPostNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.name(), exception.getMessage());
    }

    @ExceptionHandler(ContextRuntimeException.class)
    public ExceptionResponse handlerContextRuntimeException(ContextRuntimeException exception) {
        log.error("handlerContextRuntimeException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.NO_CONTENT.name(), exception.getMessage());
    }

}