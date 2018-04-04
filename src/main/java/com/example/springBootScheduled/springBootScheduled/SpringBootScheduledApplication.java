package com.example.springBootScheduled.springBootScheduled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //启动@Scheduled定时任务
public class SpringBootScheduledApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootScheduledApplication.class, args);
	}
}
