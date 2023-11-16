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
        var map = studentService.findAllStudentsByLastname();
        var foundStudentByLastname = map.keySet().stream().findFirst().get();
        var averageGradesListByLastname = map.get(foundStudentByLastname);
        System.out.printf("\nНайдено %s совпадений по фамили \"%s\": %s \n", averageGradesListByLastname.size(), foundStudentByLastname, averageGradesListByLastname);
    }
}
