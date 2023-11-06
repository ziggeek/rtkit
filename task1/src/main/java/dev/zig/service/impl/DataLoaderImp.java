package dev.zig.service.impl;

import dev.zig.service.DataLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class DataLoaderImp implements DataLoader {

    @Override
    public List<String> getData() throws IOException {
        return Files.readAllLines(Path.of("task1", "src", "main", "resources", "csv", "students.csv"));
    }
}
