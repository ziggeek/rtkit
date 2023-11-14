package dev.zig.model.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {

    private Long id;
    private String firstname;
    private String lastname;
    private Integer age;
    private Group group;
}
