package com.naveen.studentprojectv2.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class StudentConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository) {
        return args -> {
            Student naveen = new Student(
                    1L, "Naveenkumar", "naveenkumar.chandrasekhar@gmail.com",
                    LocalDate.of(1993, Month.JANUARY, 29)
            );
           studentRepository.saveAll(List.of(naveen));
        };
    }

}
