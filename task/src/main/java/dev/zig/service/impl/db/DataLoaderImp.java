package dev.zig.service.impl.db;

import dev.zig.dao.AcademicPerformanceDao;
import dev.zig.dao.StudentDao;
import dev.zig.dao.schema.ClearDbDao;
import dev.zig.dao.schema.CreateDbTableDao;
import dev.zig.dao.GroupDao;
import dev.zig.dao.schema.DropDbTableDao;
import dev.zig.exception.FileDataNotFoundException;
import dev.zig.model.dto.AcademicPerformanceDto;
import dev.zig.model.dto.GroupDto;
import dev.zig.model.dto.StudentDto;
import dev.zig.service.DataLoader;
import dev.zig.util.ConsoleHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DataLoaderImp implements DataLoader {



    @Override
    public void init_data() throws FileDataNotFoundException {

        List<String> csvData;
        try {
            csvData = Files.readAllLines(Path.of("task", "src", "main", "resources", "csv", "students.csv"));
            String size = String.format(" из %s", csvData.size());

            List<String> invalidLines = new ArrayList<>();
            for (int i = 1; i < csvData.size(); i++) {
                try {
                    ConsoleHelper.getInstance().animate(i + size);
                    var group = GroupDao.getInstance().save(GroupDto.from(csvData.get(i)));
                    var student = StudentDao.getInstance().save(StudentDto.from(csvData.get(i)), group);
                    var academicPerformances = AcademicPerformanceDao.getInstance().save(AcademicPerformanceDto.from(csvData.get(i)), student);
                } catch (IllegalArgumentException e) {
                    invalidLines.add(String.format("\nНекорректные данные в линии: %s", csvData.get(i)));
                }
            }

            if (!invalidLines.isEmpty()) invalidLines.forEach(System.err::println);

        } catch (IOException e) {
            throw new FileDataNotFoundException(e);
        }
    }

    @Override
    public void drop_table() throws IOException {
        var file = Files.readAllBytes(Path.of("task", "src", "main", "resources", "migration", "drop_table.sql"));
        String fileContent = new String(file);
        List<String> scriptsSql = Arrays.asList(fileContent.split("--"));
        DropDbTableDao.getInstance().init(scriptsSql);
    }

    @Override
    public void init_table() throws IOException {
        var file = Files.readAllBytes(Path.of("task", "src", "main", "resources", "migration", "init_table.sql"));
        String fileContent = new String(file);
        List<String> scriptsSql = Arrays.asList(fileContent.split("--"));
        CreateDbTableDao.getInstance().init(scriptsSql);
    }

    @Override
    public void clear_db() throws IOException {
        var file = Files.readAllBytes(Path.of("task", "src", "main", "resources", "migration", "clear_db.sql"));
        String fileContent = new String(file);
        List<String> scriptsSql = Arrays.asList(fileContent.split("--"));
        ClearDbDao.getInstance().clear(scriptsSql);
    }
}
