package ru.akbirov.bulatapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO запроса для добавления нового студента")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddStudentRequestDto {

    @Schema(description = "Фамилия студента", required = true)
    private String surname;

    @Schema(description = "Группа, к которой принадлежит студент", required = true)
    private GroupIdDto group;
}
