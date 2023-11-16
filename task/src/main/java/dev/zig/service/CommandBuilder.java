package dev.zig.service;

import dev.zig.exception.InvalidCommandException;
import dev.zig.service.impl.command.RestCommand;
import dev.zig.service.impl.command.StatisticCommand;

import java.io.IOException;

public class CommandBuilder {
    private final StudentService studentService = new StudentService();

    public Command buildCommand(String commandName) throws IOException {
        studentService.getDataLoader().drop_table();
        studentService.getDataLoader().init_table();
        studentService.getDataLoader().init_data();
        if (commandName.equals("statistic")) return new StatisticCommand(studentService);
        if (commandName.equals("rest")) return new RestCommand();
        throw new InvalidCommandException(commandName);
    }
}

