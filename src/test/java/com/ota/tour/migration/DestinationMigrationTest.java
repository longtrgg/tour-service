package com.ota.tour.migration;

import com.ota.tour.data.migration.DestinationMigration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DestinationMigrationTest {
    @Autowired
    private DestinationMigration destinationMigration;

    @Test
    void migration() {
        destinationMigration.migrateVN();
    }
}
