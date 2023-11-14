package dev.zig.exception;

public class InvalidLineException extends RuntimeException {

    public InvalidLineException(String line, Throwable cause) {
        super(String.format("Некорректные данные в линии: %s", line), cause);
    }
}
