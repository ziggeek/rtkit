package dev.zig.service.impl.option;

import dev.zig.dao.StudentDao;
import dev.zig.service.Option;
import dev.zig.service.StudentService;

public class SecondStatisticsCommandOption implements Option {

    private final StudentService studentService;

    public SecondStatisticsCommandOption(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        var studentList = new StudentDao().findExcellentStudentsOver14YO();
        System.out.printf("Cписок всех отличников старше 14 лет: %s\n", studentList);
    }
}
