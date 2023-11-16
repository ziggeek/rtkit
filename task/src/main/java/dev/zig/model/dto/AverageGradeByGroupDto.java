package dev.zig.model.dto;

import dev.zig.model.entity.Student;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AverageGradeByGroupDto {

    String groupNumber;
    List<Student> students;
}
