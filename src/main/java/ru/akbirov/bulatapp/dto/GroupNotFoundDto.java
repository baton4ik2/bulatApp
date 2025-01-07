package ru.akbirov.bulatapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO для ответа о том, что группа не найдена")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupNotFoundDto {

    @Schema(description = "Код ошибки")
    private int code;

    @Schema(description = "Сообщение об ошибке")
    private String message;
}