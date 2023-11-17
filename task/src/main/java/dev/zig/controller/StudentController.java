package dev.zig.controller;


import dev.zig.controller.api.StudentApi;
import dev.zig.model.dto.StudentDto;
import dev.zig.model.dto.request.UpdateGradeStudentByFioAndGroupRequest;
import dev.zig.model.dto.response.AverageGradeByGroupResponse;
import dev.zig.model.dto.response.StudentWithAverageGradeResponse;
import dev.zig.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StudentController implements StudentApi {

    private final StudentService studentService;


    @Override
    public ResponseEntity<Double> findAverageGradeForUpperclassmans() {
        var resp = studentService.findAverageGradeForUpperclassmans();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<List<StudentDto>> findExcellentStudentsOver14YO() {
        var resp = studentService.findExcellentStudentsOver14YO();
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<List<StudentWithAverageGradeResponse>> findAllStudentsByLastname(String lastname) {
        var resp = studentService.findAllStudentsByLastname(lastname);
        return ResponseEntity.ok().body(resp);
    }

    @Override
    public ResponseEntity<Void> updateGradeStudentByFioAndGroup(UpdateGradeStudentByFioAndGroupRequest req) {
        studentService.updateGradeStudentByFioAndGroup(req);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<AverageGradeByGroupResponse> findAverageGradeStudentsByGroup(String number) {
        var resp = studentService.findAverageGradeStudentsByGroup(number);
        return ResponseEntity.ok().body(resp);
    }
}
