package dev.zig.exception;

import static java.lang.String.format;

public class InvalidCommandOptionException extends RuntimeException {

    public InvalidCommandOptionException(String commandOption) {
        super(format("Invalid command option: %s", commandOption));
    }
}
