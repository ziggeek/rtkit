package dev.zig.exception;

public class FileDataNotFoundException extends RuntimeException {

    public FileDataNotFoundException(Throwable throwable) {
        super("File with data not found in config directory: ", throwable);
    }
}
