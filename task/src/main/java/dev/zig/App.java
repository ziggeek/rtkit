package dev.zig;

import dev.zig.exception.EmptyCommandException;
import dev.zig.service.CommandBuilder;
import dev.zig.service.StudentService;
import dev.zig.service.impl.command.SimpleCommand;
import dev.zig.service.impl.db.DataLoaderImp;

import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            new SimpleCommand().printHelp();
            throw new EmptyCommandException();
        } else {
            var dataLoader = new DataLoaderImp();
            var studentService = new StudentService(dataLoader);
            studentService.getDataLoader().drop_table();
            studentService.getDataLoader().init_table();
            studentService.getDataLoader().init_data();
            var commandBuilder = new CommandBuilder(studentService);
            var command = commandBuilder.buildCommand(args[0]);
            command.execute(args[1]);
        }
    }
}






