package com.railwayproject.connection;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class ConnectionConfig {

    @Bean
    CommandLineRunner commandLineRunner(ConnectionRepository repository) {
        return args -> {
            Connection wroclawWarsaw = new Connection(
                    "Wroclaw Main Station",
                    LocalDateTime.of(2022, 1, 24, 10, 30),
                    3,
                    "Warsaw West Station",
                    LocalDateTime.of(2022, 1, 24, 15, 39),
                    "IC",
                    65.75D
            );

            Connection wroclawCracow = new Connection(
                    "Wroclaw Main Station",
                    LocalDateTime.of(2022, 1, 25, 10, 36),
                    2,
                    "Cracow Main Station",
                    LocalDateTime.of(2022, 1, 25, 14, 2),
                    "IC",
                    37.50D
            );

            Connection wroclawWalbrzych = new Connection(
                    "Wroclaw Main Station",
                    LocalDateTime.of(2022, 1, 26, 15, 20),
                    6,
                    "Walbrzych Main Station",
                    LocalDateTime.of(2022, 2, 26, 16,14),
                    "KD",
                    25.25D
            );

            repository.saveAll(
                    List.of(wroclawWarsaw, wroclawCracow, wroclawWalbrzych)
            );
        };
    }
}
