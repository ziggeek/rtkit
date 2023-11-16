package dev.zig.model.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AcademicPerformance {

    private Long id;
    private Integer physics;
    private Integer mathematics;
    private Integer rus;
    private Integer literature;
    private Integer geometry;
    private Integer informatics;
    private Double averageGrade;
    private Student student;
}
