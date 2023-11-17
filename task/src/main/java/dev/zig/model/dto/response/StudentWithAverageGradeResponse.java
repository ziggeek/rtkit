package dev.zig.model.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StudentWithAverageGradeResponse {

    String firstname;
    String lastname;
    Integer age;
    Double averageGrade;
}
