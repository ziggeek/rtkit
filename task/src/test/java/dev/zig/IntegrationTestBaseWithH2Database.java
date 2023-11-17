package dev.zig;

import dev.zig.TaskApplication;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = {
        "/sql/h2/country.sql",
        "/sql/h2/state.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "/sql/h2/clean-db/clean-up.sql")
@ActiveProfiles("h2")
@SpringBootTest(classes = TaskApplication.class)
public abstract class IntegrationTestBaseWithH2Database {

    @BeforeAll
    static void init() {}

    @AfterAll
    static void afterAll() {}
}

