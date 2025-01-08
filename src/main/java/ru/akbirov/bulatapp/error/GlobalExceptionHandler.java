package ru.akbirov.bulatapp.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.akbirov.bulatapp.dto.AppErrorDto;
import ru.akbirov.bulatapp.exception.JwtAuthenticationException;
import ru.akbirov.bulatapp.exception.UserAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<AppErrorDto> handleBadCredentialsException(BadCredentialsException e) {
        return new ResponseEntity<>(new AppErrorDto(HttpStatus.UNAUTHORIZED.value(),
                "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtAuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<AppErrorDto> handleJwtAuthenticationException(JwtAuthenticationException e) {
        return new ResponseEntity<>(new AppErrorDto(HttpStatus.UNAUTHORIZED.value(),
                e.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    // Вы можете добавить другие обработчики исключений здесь
}
