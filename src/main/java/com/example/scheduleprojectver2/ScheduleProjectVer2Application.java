package com.example.scheduleprojectver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleProjectVer2Application {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleProjectVer2Application.class, args);
    }

}
