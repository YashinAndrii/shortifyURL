package com.task.shortifyURL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication
public class ShortifyUrlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShortifyUrlApplication.class, args);
	}

}
