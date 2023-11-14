package dev.zig.model.dto;

import dev.zig.model.entity.Group;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StudentDto {

    String firstname;
    String lastname;
    Integer age;
    GroupDto group;

    public static StudentDto from(String csvLine) {
        String[] data = csvLine.split(";");
        return StudentDto.builder()
                .lastname(data[0])
                .firstname(data[1])
                .age(Integer.valueOf(data[2]))
                .group(GroupDto.from(csvLine))
                .build();

    }
}
