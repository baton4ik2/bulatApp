package ru.akbirov.bulatapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO ответа для всех студентов")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllStudentResponseDto {

    @Schema(description = "Фамилия студента")
    private String surname;
    @Schema(description = "Id группы студента")
    private GroupIdDto group;
}
