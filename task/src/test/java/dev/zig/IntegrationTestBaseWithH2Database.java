package dev.zig;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class IntegrationTestBaseWithH2Database {

    @BeforeAll
    static void init() {}

    @AfterAll
    static void afterAll() {}
}
