package dev.zig.dao.schema;

import dev.zig.exception.DaoException;
import dev.zig.util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DropDbTableDao {

    private static final DropDbTableDao INSTANCE = new DropDbTableDao();

    public DropDbTableDao() {
    }

    public static DropDbTableDao getInstance() {
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
            System.out.print("\nDROP-migration is successful.");
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }
}
