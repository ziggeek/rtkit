package dev.zig.mapper;

import dev.zig.model.dto.StudentDto;
import dev.zig.model.dto.response.StudentWithAverageGradeResponse;
import dev.zig.model.entity.AcademicPerformance;
import dev.zig.model.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {
        StudentMapper.class,
        GroupMapper.class},
        componentModel = "spring"
)
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(target = "averageGrade", source = "academicPerformance.averageGrade")
    StudentWithAverageGradeResponse fromEntity(Student student);

    @Mapping(target = "firstname", source = "performance.student.firstname")
    @Mapping(target = "lastname", source = "performance.student.lastname")
    @Mapping(target = "age", source = "performance.student.age")
    @Mapping(target = "group", source = "performance.student.group")
    StudentDto toDto(AcademicPerformance performance);

    List<StudentDto> toDto(List<AcademicPerformance> student);

    List<StudentWithAverageGradeResponse> fromEntity(List<Student> students);
}
