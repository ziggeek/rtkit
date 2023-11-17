package dev.zig.service;

import dev.zig.model.dto.StudentDto;
import dev.zig.model.dto.request.UpdateGradeStudentByFioAndGroupRequest;
import dev.zig.model.dto.response.AverageGradeByGroupResponse;
import dev.zig.model.dto.response.StudentWithAverageGradeResponse;

import java.util.List;

public interface StudentService {

    public Double findAverageGradeForUpperclassmans();

    public List<StudentDto> findExcellentStudentsOver14YO();

    public List<StudentWithAverageGradeResponse> findAllStudentsByLastname(String lastname);

    public void updateGradeStudentByFioAndGroup(UpdateGradeStudentByFioAndGroupRequest req);

    public AverageGradeByGroupResponse findAverageGradeStudentsByGroup(String number);
}
