package ru.akbirov.bulatapp.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.akbirov.bulatapp.dto.GroupNotFoundDto;
import ru.akbirov.bulatapp.exception.GroupNotFoundException;

@RestControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = {GroupNotFoundException.class})
    protected ResponseEntity<GroupNotFoundDto> handleGroupNotFound() {
        log.info("Group Not Found");
        return ResponseEntity
                .status(400)
                .body(new GroupNotFoundDto(400, "Group not found"));

    }
}
