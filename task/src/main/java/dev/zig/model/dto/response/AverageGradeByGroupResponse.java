package dev.zig.model.dto.response;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class AverageGradeByGroupResponse {

    String groupNumber;
    List<StudentWithAverageGradeResponse> students;
}
