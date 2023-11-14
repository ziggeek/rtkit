package dev.zig.exception;

import static java.lang.String.format;

public class InvalidCommandException extends RuntimeException {

    public InvalidCommandException(String command) {
        super(format("Invalid command: %s", command));
    }
}
