package ru.akbirov.bulatapp.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO ответа для одной группы")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponseDto {

    @Schema(description = "ID группы")
    private int id;

    @Schema(description = "Номер группы")
    private String groupNumber;
}
