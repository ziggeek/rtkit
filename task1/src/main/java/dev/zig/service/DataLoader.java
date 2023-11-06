package dev.zig.service;

import java.io.IOException;
import java.util.List;

public interface DataLoader {

    List<String> getData() throws IOException;
}
