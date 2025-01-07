package ru.akbirov.bulatapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.akbirov.bulatapp.entity.Group;

@Schema(description = "DTO запроса для добавления нового студента")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddStudentRequestDto {

    @Schema(description = "Фамилия студента", required = true)
    private String surname;

    @Schema(description = "Группа, к которой принадлежит студент", required = true)
    private GroupIdDto group;
}
