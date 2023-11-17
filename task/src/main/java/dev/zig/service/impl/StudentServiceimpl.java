package dev.zig.service.impl;


import dev.zig.mapper.StudentMapper;
import dev.zig.model.dto.StudentDto;
import dev.zig.model.dto.request.UpdateGradeStudentByFioAndGroupRequest;
import dev.zig.model.dto.response.AverageGradeByGroupResponse;
import dev.zig.model.dto.response.StudentWithAverageGradeResponse;
import dev.zig.repository.AcademicPerformanceRepository;
import dev.zig.repository.StudentRepository;
import dev.zig.repository.jdbc.StudentDao;
import dev.zig.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceimpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AcademicPerformanceRepository academicPerformanceRepository;
    private final StudentDao studentDao;

    @Override
    public Double findAverageGradeForUpperclassmans() {
        return studentRepository.findAverageGradeForUpperclassmans();
    }

    @Override
    public List<StudentDto> findExcellentStudentsOver14YO() {
        var academicPerformances = academicPerformanceRepository.findExcellentStudentsOver14YO();
        return StudentMapper.INSTANCE.toDto(academicPerformances);
    }

    @Override
    public List<StudentWithAverageGradeResponse> findAllStudentsByLastname(String lastname) {
        var students = studentRepository.findStudentsByLastnameLikeIgnoreCase(lastname);
        return StudentMapper.INSTANCE.fromEntity(students);
    }

    @Override
    public void updateGradeStudentByFioAndGroup(UpdateGradeStudentByFioAndGroupRequest req) {
        studentDao.updateGradeStudentByFioAndGroup(req);
    }

    @Override
    public AverageGradeByGroupResponse findAverageGradeStudentsByGroup(String number) {
        var students = studentRepository.findAverageGradeStudentsByGroup(number);
        var listDtos = StudentMapper.INSTANCE.fromEntity(students);
        return AverageGradeByGroupResponse.builder().groupNumber(number).students(listDtos).build();
    }
}
