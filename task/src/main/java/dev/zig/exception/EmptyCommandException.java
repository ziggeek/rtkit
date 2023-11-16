package dev.zig.exception;

public class EmptyCommandException extends RuntimeException {

    public EmptyCommandException() {
        super("Empty command not supported");
    }
}
