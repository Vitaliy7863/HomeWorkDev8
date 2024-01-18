package org.example;

import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {
        Flyway flyway = Flyway.configure().dataSource("jdbc:h2:./test;", "sa", "")
                .locations("filesystem:src/main/resources/db.migration")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}