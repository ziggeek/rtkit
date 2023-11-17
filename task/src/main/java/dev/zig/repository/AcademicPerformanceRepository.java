package dev.zig.repository;

import dev.zig.model.entity.AcademicPerformance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AcademicPerformanceRepository extends JpaRepository<AcademicPerformance, Long> {

    @Query("SELECT ag1 FROM AcademicPerformance ag1 " +
            "WHERE ag1.averageGrade = 5 " +
            "AND ag1.student IN (SELECT st2 " +
            "FROM Student st2 " +
            "WHERE st2.age >= 15)"
    )
    List<AcademicPerformance> findExcellentStudentsOver14YO();
}
