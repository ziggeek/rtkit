package dev.zig.model.dto;

import dev.zig.model.entity.Student;
import lombok.Builder;
import lombok.Value;


@Value
@Builder
public class AcademicPerformanceDto {

    Integer physics;
    Integer mathematics;
    Integer rus;
    Integer literature;
    Integer geometry;
    Integer informatics;
    StudentDto student;

    public static AcademicPerformanceDto from(String csvLine) {
        String[] data = csvLine.split(";");
        return AcademicPerformanceDto.builder()
                .physics(Integer.valueOf(data[4]))
                .mathematics(Integer.valueOf(data[5]))
                .rus(Integer.valueOf(data[6]))
                .literature(Integer.valueOf(data[7]))
                .geometry(Integer.valueOf(data[8]))
                .informatics(Integer.valueOf(data[9]))
                .student(StudentDto.from(csvLine))
                .build();
    }
}
