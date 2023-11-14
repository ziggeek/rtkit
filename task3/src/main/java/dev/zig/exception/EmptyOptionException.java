package dev.zig.exception;

public class EmptyOptionException extends RuntimeException {

    public EmptyOptionException() {
        super("Empty option not supported");
    }
}
