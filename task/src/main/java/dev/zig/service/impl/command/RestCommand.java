package dev.zig.service.impl.command;

import dev.zig.service.Command;
import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Path;

public class RestCommand implements Command {

    public RestCommand() {
    }

    @Override
    public void execute(String option) {
    }

    @SneakyThrows
    @Override
    public void execute() {
        var httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpRequest request1 = HttpRequest.newBuilder(URI.create("http://localhost/find-average-grade-students-by-group"))
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of("task", "src", "main", "resources", "json", "req1.json")))
                .header("content-type", "application/json")
                .build();

        HttpRequest request2 = HttpRequest.newBuilder(URI.create("http://localhost/update-grade-student-by-fio-and-group"))
                .POST(HttpRequest.BodyPublishers.ofFile(Path.of("task", "src", "main", "resources", "json", "req2.json")))
                .header("content-type", "application/json")
                .build();
    }
}
