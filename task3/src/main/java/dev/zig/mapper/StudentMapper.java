package dev.zig.mapper;

import dev.zig.model.dto.StudentWithAverageGradeDto;
import dev.zig.model.entity.AcademicPerformance;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {

    private static final StudentMapper INSTANCE = new StudentMapper();

    public StudentMapper() {
    }

    public static StudentMapper getInstance() {
        return INSTANCE;
    }

    public StudentWithAverageGradeDto fromEntity(AcademicPerformance academicPerformance) {
        return StudentWithAverageGradeDto.builder()
                .firstname(academicPerformance.getStudent().getFirstname())
                .lastname(academicPerformance.getStudent().getLastname())
                .age(academicPerformance.getStudent().getAge())
                .averageGrade(academicPerformance.getAverageGrade())
                .build();
    }

    public List<StudentWithAverageGradeDto> fromEntity(List<AcademicPerformance> academicPerformance) {
        List<StudentWithAverageGradeDto> list = new ArrayList<>();
        for (AcademicPerformance performance : academicPerformance) {
            list.add(fromEntity(performance));
        }
        return list;
    }

}
