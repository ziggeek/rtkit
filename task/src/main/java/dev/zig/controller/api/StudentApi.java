package dev.zig.controller.api;

import dev.zig.model.dto.StudentDto;
import dev.zig.model.dto.request.UpdateGradeStudentByFioAndGroupRequest;
import dev.zig.model.dto.response.AverageGradeByGroupResponse;
import dev.zig.model.dto.response.StudentWithAverageGradeResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static dev.zig.util.UrlPath.*;

@Validated
@Tag(name = "StudentApi", description = "Student API")
public interface StudentApi {

    @GetMapping(FIND_AVERAGE_GRADE_FOR_UPPERCLASSMANS)
    ResponseEntity<Double> findAverageGradeForUpperclassmans();

    @GetMapping(FIND_EXCELLENT_STUDENTS_OVER_14_YO)
    ResponseEntity<List<StudentDto>> findExcellentStudentsOver14YO();

    @GetMapping(FIND_ALL_STUDENTS_BY_LASTNAME)
    ResponseEntity<List<StudentWithAverageGradeResponse>> findAllStudentsByLastname(@RequestParam("lastname") String lastname);

    @PatchMapping(UPDATE_GRADE_STUDENT_BY_FIO_AND_GROUP)
    ResponseEntity<Void> updateGradeStudentByFioAndGroup(@RequestBody UpdateGradeStudentByFioAndGroupRequest req);

    @GetMapping(FIND_AVERAGE_GRADE_STUDENTS_BY_GROUP)
    ResponseEntity<AverageGradeByGroupResponse> findAverageGradeStudentsByGroup(@RequestParam("number") String number);


}
