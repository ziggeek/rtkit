package dev.zig.dao;

import dev.zig.exception.DaoException;
import dev.zig.model.entity.Group;
import dev.zig.model.dto.GroupDto;
import dev.zig.util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupDao {

    private static final String SAVE_SQL =
            "INSERT INTO t_group (t_number) " +
                    "VALUES (?);";

    private static final String FIND_BY_ID_SQL =
            "SELECT id, t_number " +
                    "FROM t_group " +
                    "WHERE id = ?";

    private static final GroupDao INSTANCE = new GroupDao();

    public GroupDao() {
    }

    public static GroupDao getInstance() {
        return INSTANCE;
    }

    public Group save(GroupDto dto) {
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, dto.getNumber());
            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            var group = Group.builder();
            if (generatedKeys.next()) {
                group.id(generatedKeys.getLong("id"));
            }

            return group
                    .number(dto.getNumber())
                    .build();
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }

    public Optional<Group> findById(Long id) {
        try (var connection = ConnectionManager.get()
        ) {
            try (var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)
            ) {
                preparedStatement.setLong(1, id);

                var resultSet = preparedStatement.executeQuery();
                Group group = null;
                if (resultSet.next()) {
                    group = Group.builder()
                            .id(resultSet.getLong("id"))
                            .number(resultSet.getString("t_number"))
                            .build();
                }
                return Optional.ofNullable(group);
            } catch (SQLException throwables) {
                throw new DaoException(throwables);
            }
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }
}
