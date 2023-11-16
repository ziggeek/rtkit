package dev.zig.dao.schema;

import dev.zig.exception.DaoException;
import dev.zig.util.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClearDbDao {

    private static final ClearDbDao INSTANCE = new ClearDbDao();

    public ClearDbDao() {
    }

    public static ClearDbDao getInstance() {
        return INSTANCE;
    }

    public void clear(List<String> scripts) {
        try (var connection = ConnectionManager.get();
             Statement statement = connection.createStatement()
        ) {
            for (String script : scripts) {
                statement.addBatch(script);
            }
            statement.executeBatch();
            System.err.print("\nCLEAR-migration is successful.");
        } catch (SQLException throwables) {
            throw new DaoException(throwables);
        }
    }
}
