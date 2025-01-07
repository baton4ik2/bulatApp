package ru.akbirov.bulatapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO ответа для всех групп")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllGroupResponseDto {

    @Schema(description = "ID группы")
    private int id;

    @Schema(description = "Номер группы")
    private String groupNumber;

    @Schema(description = "Количество студентов в группе")
    private int quantity;
}