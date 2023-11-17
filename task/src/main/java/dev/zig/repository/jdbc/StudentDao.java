package dev.zig.repository.jdbc;

import dev.zig.model.dto.request.UpdateGradeStudentByFioAndGroupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public class StudentDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void updateGradeStudentByFioAndGroup(UpdateGradeStudentByFioAndGroupRequest req) {
        var sqlQuery =
                "UPDATE academic_performance " +
                        "SET " + req.getSubject() + " = " + req.getGrade() + " " +
                        "WHERE academic_performance.student_id IN (SELECT student.id FROM student " +
                        "JOIN t_group ON student.group_id = t_group.id " +
                        "WHERE student.firstname = :firstname AND student.lastname = :lastname AND t_group.t_number = :number)";
        var map = Map.of(
                "firstname", req.getFirstname(),
                "number", req.getNumber(),
                "lastname", req.getLastname()
        );
        namedParameterJdbcTemplate.execute(sqlQuery, map, (PreparedStatementCallback) ps -> ps.executeUpdate());
    }

}
