package com.myakishev.magistracyTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.awt.*;

@SpringBootApplication
@EnableScheduling
public class MagistracyTrackerApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(MagistracyTrackerApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

}
