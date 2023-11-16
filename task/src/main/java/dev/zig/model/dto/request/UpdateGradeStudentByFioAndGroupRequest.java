package dev.zig.model.dto.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateGradeStudentByFioAndGroupRequest {

    String firstname;
    String lastname;
    String number;
    Integer grade;
    String subject;
}
