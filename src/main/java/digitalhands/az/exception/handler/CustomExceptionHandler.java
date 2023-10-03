package digitalhands.az.exception.handler;

import digitalhands.az.exception.IncorrectPasswordException;
import digitalhands.az.exception.UserAlreadyException;
import digitalhands.az.exception.UserNotFoundException;
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
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ExceptionResponse handlerIncorrectPasswordException(IncorrectPasswordException exception) {
        log.error("handlerIncorrectPasswordException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.BAD_REQUEST.toString(), exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponse handlerUserNotFoundException(UserNotFoundException exception) {
        log.error("handlerUserNotFoundException {}", exception.getMessage());
        return new ExceptionResponse(HttpStatus.NOT_FOUND.name(), exception.getMessage());
    }

}