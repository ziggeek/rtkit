package dev.zig.service.impl.command;

import dev.zig.exception.InvalidCommandOptionException;
import dev.zig.service.Command;
import dev.zig.service.StudentService;
import dev.zig.service.impl.option.FirstStatisticsCommandOption;
import dev.zig.service.impl.option.SecondStatisticsCommandOption;
import dev.zig.service.impl.option.ThirdStatisticsCommandOption;

public class StatisticCommand implements Command {

    private final StudentService studentService;

    public StatisticCommand(StudentService studentService) {
        this.studentService = studentService;
    }


    @Override
    public void execute(String option) {
        switch (option) {
            case "--first":
                new FirstStatisticsCommandOption(studentService).execute();
                break;
            case "--second":
                new SecondStatisticsCommandOption(studentService).execute();
                break;
            case "--third":
                new ThirdStatisticsCommandOption(studentService).execute();
                break;
            default:
                printHelp();
                throw new InvalidCommandOptionException(option);
        }
    }
}
