package dev.zig.model.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@Table(name = "academic_performance")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AcademicPerformance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private Integer physics;
    private Integer mathematics;
    private Integer rus;
    private Integer literature;
    private Integer geometry;
    private Integer informatics;
    private Double averageGrade;

    @OneToOne
    @JoinColumn(name = "student_id")
    @ToString.Exclude
    private Student student;
}
