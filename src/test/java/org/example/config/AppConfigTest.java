package org.example.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AppConfigTest {

    @Test
    void createAppConfig() {
        AppConfig appConfig = new AppConfig();
        assertNotNull(appConfig);
    }

}