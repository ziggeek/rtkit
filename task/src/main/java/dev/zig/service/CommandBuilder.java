package dev.zig.service;

import dev.zig.exception.EmptyCommandException;
import dev.zig.exception.InvalidCommandException;
import dev.zig.service.impl.command.SimpleCommand;
import dev.zig.service.impl.command.StatisticCommand;

public class CommandBuilder {
    private final StudentService studentService;

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
    }

    public Command buildCommand(String commandName) {
        if (commandName.equals("statistic")) return new StatisticCommand(studentService);
        throw new InvalidCommandException(commandName);
    }
}

