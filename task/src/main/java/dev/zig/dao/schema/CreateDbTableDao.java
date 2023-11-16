package dev.zig.dao.schema;

import dev.zig.exception.DaoException;
import dev.zig.util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CreateDbTableDao {

    private static final CreateDbTableDao INSTANCE = new CreateDbTableDao();

    public CreateDbTableDao() {
    }

    public static CreateDbTableDao getInstance() {
        return INSTANCE;
    }

    public void init(List<String> scripts) {
        try (var connection = ConnectionManager.get();
             Statement statement = connection.createStatement()
        ) {
            for (String script : scripts) {
                statement.addBatch(script);
            }
            statement.executeBatch();
            System.out.println("\nCREATE-migration is successful.\n");
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }
}
