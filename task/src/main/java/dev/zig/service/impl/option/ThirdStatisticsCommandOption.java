package dev.zig.service.impl.option;

import dev.zig.dao.AcademicPerformanceDao;
import dev.zig.service.Option;
import dev.zig.service.StudentService;

import java.util.Scanner;

public class ThirdStatisticsCommandOption implements Option {

    private final StudentService studentService;

    public ThirdStatisticsCommandOption(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.err.print("Input lastname for finding: ");
            String findStudentByLastname = scanner.next();
            var averageGradeByLastname = new AcademicPerformanceDao().findAllStudentsByLastname(findStudentByLastname);
            System.out.printf("Найдено %s совпадений по фамили \"%s\": %s \n", averageGradeByLastname.size(), findStudentByLastname, averageGradeByLastname);
        }
    }
}
