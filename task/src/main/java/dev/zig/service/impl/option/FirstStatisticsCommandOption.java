package dev.zig.service.impl.option;

import dev.zig.dao.StudentDao;
import dev.zig.service.Option;
import dev.zig.service.StudentService;


public class FirstStatisticsCommandOption implements Option {

    private final StudentService studentService;

    public FirstStatisticsCommandOption(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        var averageGrade = new StudentDao().findAverageGradeForUpperclassmans();
        System.out.printf("Средний балл старшекласника: %s\n", averageGrade);
    }
}
