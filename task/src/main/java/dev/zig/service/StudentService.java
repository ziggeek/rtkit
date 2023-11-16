package dev.zig.service;


import dev.zig.dao.AcademicPerformanceDao;
import dev.zig.dao.StudentDao;
import dev.zig.model.dto.StudentWithAverageGradeDto;
import dev.zig.model.entity.Student;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Getter
public class StudentService {

    private DataLoader dataLoader;

    public StudentService(DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public Double findAverageGradeForUpperclassmans() {
        return new StudentDao().findAverageGradeForUpperclassmans();
    }

    public List<Student> findExcellentStudentsOver14YO() {
        return new StudentDao().findExcellentStudentsOver14YO();
    }

    public Map<String, List<StudentWithAverageGradeDto>> findAllStudentsByLastname() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.err.print("Input lastname for finding: ");
            String findStudentByLastname = scanner.next();
            return Map.of(findStudentByLastname, new AcademicPerformanceDao().findAllStudentsByLastname(findStudentByLastname));
        }
    }
}
