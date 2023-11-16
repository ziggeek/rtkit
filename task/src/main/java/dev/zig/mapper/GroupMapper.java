package dev.zig.mapper;

import dev.zig.model.dto.AverageGradeByGroupDto;
import dev.zig.model.entity.Student;

import java.util.List;

public class GroupMapper {

    private static final GroupMapper INSTANCE = new GroupMapper();

    public GroupMapper() {
    }

    public static GroupMapper getInstance() {
        return INSTANCE;
    }


    public AverageGradeByGroupDto from(List<Student> students) {
        return AverageGradeByGroupDto.builder()
                .groupNumber(students.stream().findFirst().get().toString())
                .students(students)
                .build();
    }
}
