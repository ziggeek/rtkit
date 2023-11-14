package dev.zig.dao;

import dev.zig.exception.DaoException;
import dev.zig.mapper.StudentMapper;
import dev.zig.model.dto.AcademicPerformanceDto;
import dev.zig.model.dto.StudentDto;
import dev.zig.model.dto.StudentWithAverageGradeDto;
import dev.zig.model.entity.AcademicPerformance;
import dev.zig.model.entity.Group;
import dev.zig.model.entity.Student;
import dev.zig.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AcademicPerformanceDao {

    private static final String SAVE_SQL =
            "INSERT INTO academic_performance (physics, mathematics, rus, literature, geometry, informatics, student_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?);";


    private static final String FIND_STUDENTS_BY_LASTNAME =
            "SELECT academic_performance.id, student_id, group_id, firstname, lastname, age, t_number, average_grade FROM academic_performance " +
                    "JOIN student ON academic_performance.student_id = student.id " +
                    "JOIN t_group ON student.group_id = t_group.id " +
                    "WHERE lastname LIKE ?";

    private static final AcademicPerformanceDao INSTANCE = new AcademicPerformanceDao();

    public AcademicPerformanceDao() {
    }

    public static AcademicPerformanceDao getInstance() {
        return INSTANCE;
    }

    public AcademicPerformance save(AcademicPerformanceDto dto, Student student) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setInt(1, dto.getPhysics());
            preparedStatement.setInt(2, dto.getMathematics());
            preparedStatement.setInt(3, dto.getRus());
            preparedStatement.setInt(4, dto.getLiterature());
            preparedStatement.setInt(5, dto.getGeometry());
            preparedStatement.setInt(6, dto.getInformatics());
            preparedStatement.setLong(7, student.getId());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            var academicPerformance = AcademicPerformance.builder();
            if (generatedKeys.next()) {
                academicPerformance.id(generatedKeys.getLong("id"));
            }

            return academicPerformance
                    .physics(dto.getPhysics())
                    .mathematics(dto.getMathematics())
                    .rus(dto.getRus())
                    .literature(dto.getLiterature())
                    .geometry(dto.getGeometry())
                    .informatics(dto.getInformatics())
                    .student(student)
                    .build();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }


    public List<StudentWithAverageGradeDto> findAllStudentsByLastname(String lastname) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_STUDENTS_BY_LASTNAME);

        ) {
            preparedStatement.setString(1, lastname + "%");
            var resultSet = preparedStatement.executeQuery();
            List<AcademicPerformance> academicPerformances = new ArrayList<>();
            while (resultSet.next()) {
                academicPerformances.add(buildAcademicPerformance(resultSet));
            }
            return StudentMapper.getInstance().fromEntity(academicPerformances);
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private AcademicPerformance buildAcademicPerformance(ResultSet resultSet) throws SQLException {
        return AcademicPerformance.builder()
                .id(resultSet.getLong("id"))
                .averageGrade(resultSet.getDouble("average_grade"))
                .student(Student.builder()
                        .id(resultSet.getLong("student_id"))
                        .firstname(resultSet.getString("firstname"))
                        .lastname(resultSet.getString("lastname"))
                        .age(resultSet.getInt("age"))
                        .group(Group
                                .builder()
                                .id(resultSet.getLong("group_id"))
                                .number(resultSet.getString("t_number"))
                                .build())
                        .build())
                .build();
    }
}
