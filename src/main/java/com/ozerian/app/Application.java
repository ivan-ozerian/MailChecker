package com.ozerian.app;

import com.ozerian.app.gui.controller.ProfilesWindowController;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Main Spring Boot class for application launch.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableScheduling
public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class)
                .headless(false)
                .web(false)
                .run(args);

        // get bean of main frame controller for swing+spring boot application launch.
        ProfilesWindowController controller = context.getBean(ProfilesWindowController.class);
        controller.prepareAndOpenFrame();
    }
}
