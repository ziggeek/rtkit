package dev.zig.service;

import java.io.IOException;

public interface DataLoader {

    void init_data() throws IOException;

    void init_table() throws IOException;

    void drop_table() throws IOException;

    void clear_db() throws IOException;
}
