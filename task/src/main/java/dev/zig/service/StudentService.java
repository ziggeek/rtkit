package dev.zig.service;


import dev.zig.dao.AcademicPerformanceDao;
import dev.zig.dao.StudentDao;
import dev.zig.mapper.GroupMapper;
import dev.zig.model.dto.response.AverageGradeByGroupResponse;
import dev.zig.model.dto.StudentWithAverageGradeDto;
import dev.zig.model.entity.Student;
import dev.zig.service.impl.db.DataLoaderImp;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Getter
public class StudentService {

    private final DataLoader dataLoader = new DataLoaderImp();
    private final StudentDao studentDao = new StudentDao();
    private final AcademicPerformanceDao academicPerformanceDao = new AcademicPerformanceDao();

    public StudentService() {
    }

    public Double findAverageGradeForUpperclassmans() {
        return studentDao.findAverageGradeForUpperclassmans();
    }

    public List<Student> findExcellentStudentsOver14YO() {
        return studentDao.findExcellentStudentsOver14YO();
    }

    public Map<String, List<StudentWithAverageGradeDto>> findAllStudentsByLastname() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.err.print("Input lastname for finding: ");
            var findStudentByLastname = scanner.next();

            var list = academicPerformanceDao.findAllStudentsByLastname(findStudentByLastname);

            return Map.of(findStudentByLastname, list);
        }
    }

    public void updateGradeStudentByFioAndGroup(String firstname, String lastname, String number, String subject, Integer grade) {
        academicPerformanceDao.updateGradeStudentByFioAndGroup(firstname, lastname, number, subject, grade);
    }

    public AverageGradeByGroupResponse findAverageGradeStudentsByGroup(String number) {
        return studentDao.findAverageGradeStudentsByGroup(number);
    }


}
