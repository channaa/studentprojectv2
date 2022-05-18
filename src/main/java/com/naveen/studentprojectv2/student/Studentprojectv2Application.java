package com.naveen.studentprojectv2.student;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com/naveen/studentprojectv2")
public class Studentprojectv2Application  {

	public static void main(String[] args) {

		SpringApplication.run(Studentprojectv2Application.class, args);
	}

}
