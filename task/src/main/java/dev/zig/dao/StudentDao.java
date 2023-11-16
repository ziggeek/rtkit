package dev.zig.dao;

import dev.zig.exception.DaoException;
import dev.zig.model.dto.GroupDto;
import dev.zig.model.dto.StudentDto;
import dev.zig.model.entity.Group;
import dev.zig.model.entity.Student;
import dev.zig.util.ConnectionManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private static final String SAVE_SQL = "INSERT INTO student (firstname, lastname, age, group_id) VALUES (?, ?, ?, ?);";
    private static final String FIND_AVERAGE_GRADE_FOR_UPPERCLASSMANS =
            "SELECT AVG(academic_performance.average_grade) AS average FROM academic_performance " +
                    "JOIN student ON academic_performance.student_id = student.id " +
                    "JOIN t_group ON student.group_id = t_group.id " +
                    "WHERE LENGTH(t_group.t_number) >= 2";

    private static final String FIND_EXCELLENT_STUDENTS_OVER_14_YO =
            "SELECT student.id, firstname, lastname, age, group_id FROM student " +
                    "JOIN academic_performance ON student.id = academic_performance.student_id " +
                    "JOIN t_group ON student.group_id = t_group.id " +
                    "WHERE age >= 14 AND academic_performance.average_grade = 5";


    private static final StudentDao INSTANCE = new StudentDao();

    public StudentDao() {
    }

    public static StudentDao getInstance() {
        return INSTANCE;
    }

    public Student save(StudentDto dto, Group group) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, dto.getFirstname());
            preparedStatement.setString(2, dto.getLastname());
            preparedStatement.setLong(3, dto.getAge());
            preparedStatement.setLong(4, group.getId());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            var student = Student.builder();
            if (generatedKeys.next()) {
                student.id(generatedKeys.getLong("id"));
            }

            return student
                    .firstname(dto.getFirstname())
                    .lastname(dto.getLastname())
                    .age(dto.getAge())
                    .group(group)
                    .build();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Double findAverageGradeForUpperclassmans() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_AVERAGE_GRADE_FOR_UPPERCLASSMANS)) {
            var resultSet = preparedStatement.executeQuery();
            Double average = null;
            while (resultSet.next()) {
                average = resultSet.getDouble("average");
            }
            return average;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public List<Student> findExcellentStudentsOver14YO() {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(FIND_EXCELLENT_STUDENTS_OVER_14_YO)) {
            var resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(buildStudent(resultSet));
            }
            return students;
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    private Student buildStudent(ResultSet resultSet) throws SQLException {
        return Student.builder()
                .id(resultSet.getLong("id"))
                .firstname(resultSet.getString("firstname"))
                .lastname(resultSet.getString("lastname"))
                .age(resultSet.getInt("age"))
                .group(GroupDao.getInstance().findById(resultSet.getLong("group_id")).get())
                .build();
    }
}
