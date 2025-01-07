package ru.akbirov.bulatapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description = "DTO запроса для обновления группы")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupUpdateRequestDto {

    @Schema(description = "ID группы для обновления", required = true)
    private int id;

    @Schema(description = "Новый номер группы", required = true)
    private String groupNumber;
}
