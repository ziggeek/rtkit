package dev.zig.service.impl;

import dev.zig.exception.FileDataNotFoundException;
import dev.zig.model.dto.AcademicPerformanceDto;
import dev.zig.model.dto.GroupDto;
import dev.zig.model.dto.StudentDto;
import dev.zig.model.entity.AcademicPerformance;
import dev.zig.model.entity.Group;
import dev.zig.model.entity.Student;
import dev.zig.repository.AcademicPerformanceRepository;
import dev.zig.repository.GroupRepository;
import dev.zig.repository.StudentRepository;
import dev.zig.service.DataLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.lang.String.format;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataLoaderImp implements DataLoader {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final AcademicPerformanceRepository academicPerformanceRepository;

    @Override
    @PostConstruct
    public void init_data() throws FileDataNotFoundException {

        List<String> csvData;
        try {
            csvData = Files.readAllLines(Path.of("task", "src", "main", "resources", "csv", "students.csv"));

            for (int i = 1; i < csvData.size(); i++) {
                try {

                    var groupDto = GroupDto.from(csvData.get(i));
                    var group = groupRepository.save(Group
                            .builder()
                            .number(groupDto.getNumber())
                            .build()
                    );

                    var studentDto = StudentDto.from(csvData.get(i));
                    var student = studentRepository.save(Student
                            .builder()
                            .firstname(studentDto.getFirstname())
                            .lastname(studentDto.getLastname())
                            .age(studentDto.getAge())
                            .group(group)
                            .build()
                    );

                    var academicPerformanceDto = AcademicPerformanceDto.from(csvData.get(i));
                    var academicPerformances = academicPerformanceRepository.save(AcademicPerformance
                            .builder()
                            .physics(academicPerformanceDto.getPhysics())
                            .mathematics(academicPerformanceDto.getMathematics())
                            .rus(academicPerformanceDto.getRus())
                            .literature(academicPerformanceDto.getLiterature())
                            .geometry(academicPerformanceDto.getGeometry())
                            .informatics(academicPerformanceDto.getInformatics())
                            .student(student)
                            .build()
                    );
                } catch (IllegalArgumentException e) {
                    System.out.println(format("Некорректные данные в линии: %s", csvData.get(i)));
                }
            }

        } catch (IOException e) {
            throw new FileDataNotFoundException(e);
        }
    }
}
