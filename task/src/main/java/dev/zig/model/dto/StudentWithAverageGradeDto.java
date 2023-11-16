package dev.zig.model.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StudentWithAverageGradeDto {

    String firstname;
    String lastname;
    Integer age;
    Double averageGrade;
}
