package dev.zig.exception;

import java.io.IOException;

public class FileDataNotFoundException extends IOException {

    public FileDataNotFoundException(Throwable throwable) {
        super("File with data not found in config directory: ", throwable);
    }
}
