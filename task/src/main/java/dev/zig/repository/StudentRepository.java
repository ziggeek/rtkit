package dev.zig.repository;

import dev.zig.model.entity.Group;
import dev.zig.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {


    @Query(value = "SELECT AVG(academic_performance.average_grade) AS average FROM academic_performance " +
            "JOIN student ON academic_performance.student_id = student.id " +
            "JOIN t_group ON student.group_id = t_group.id " +
            "WHERE LENGTH(t_group.t_number) >= 2", nativeQuery = true)
    Double findAverageGradeForUpperclassmans();

//    @Query(value = "SELECT * FROM student " +
//            "JOIN academic_performance ON student.id = academic_performance.student_id " +
//            "JOIN t_group ON student.group_id = t_group.id " +
//            "WHERE age >= 14 AND academic_performance.average_grade = 5", nativeQuery = true)
    @Query(value = "SELECT * FROM student " +
            "WHERE age >= 14", nativeQuery = true)
    List<Student> findStudentsOver14YO();
//    List<Student> findExcellentStudentsOver14YO();

    List<Student> findStudentsByLastnameLikeIgnoreCase(String lastname);

    @Query(value = "SELECT * FROM student " +
            "JOIN academic_performance ON student.id = academic_performance.student_id " +
            "JOIN t_group ON student.group_id = t_group.id " +
            "WHERE t_number = :groupNumber", nativeQuery = true)
    List<Student> findAverageGradeStudentsByGroup(@Param("groupNumber") String groupNumber);

}
