package ru.akbirov.bulatapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.*;

@Schema(description = "DTO запроса для добавления новой группы")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddGroupRequestDto {

    @Schema(description = "Номер группы", required = true)
    private String groupNumber;
}