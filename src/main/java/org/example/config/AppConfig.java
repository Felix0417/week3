package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import({JpaConfig.class, WebConfig.class})
@PropertySource({"classpath:application.properties"})
@ComponentScan("org.example")
public class AppConfig implements WebMvcConfigurer {

    public AppConfig() {
        //OK
    }
}
