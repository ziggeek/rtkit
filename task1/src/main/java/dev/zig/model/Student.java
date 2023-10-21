package dev.zig.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {

    private String firstname;
    private String lastname;
    private Integer age;
    private String group;
    private Boolean isUpperclassman;
    private AcademicPerformance performance;

    public static Student from(String csvLine) {
        String[] data = csvLine.split(";");
        return Student.builder()
                .lastname(data[0])
                .firstname(data[1])
                .age(Integer.valueOf(data[2]))
                .group(data[3])
                .isUpperclassman(checkIsUpperclassman(data[3]))
                .performance(AcademicPerformance.from(data))
                .build();

    }

    private static Boolean checkIsUpperclassman(String group) {
        return group.length() != 1 && !Character.isLetter(group.charAt(1));
    }
}
