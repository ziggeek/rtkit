package dev.zig.mapper;

import dev.zig.model.dto.StudentWithAverageGradeDto;
import dev.zig.model.dto.response.AverageGradeByGroupResponse;
import dev.zig.model.entity.Student;

import java.util.List;

public class GroupMapper {

    private static final GroupMapper INSTANCE = new GroupMapper();

    public GroupMapper() {
    }

    public static GroupMapper getInstance() {
        return INSTANCE;
    }


    public AverageGradeByGroupResponse from(List<StudentWithAverageGradeDto> students) {
        return AverageGradeByGroupResponse.builder()
                .groupNumber(students.stream().findFirst().get().toString())
                .students(students)
                .build();
    }
}
