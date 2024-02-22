package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student daniel = new Student(
                    "Daniel",
                    LocalDate.of(2002, Month.JANUARY, 4),
                    "daniel@gmail.com"
            );
            Student alex = new Student(
                    "Alex",
                    LocalDate.of(2002, Month.JANUARY, 4),
                    "alex@gmail.com"
            );
            repository.saveAll(
                    List.of(daniel, alex)
            );
        };
    }
}
