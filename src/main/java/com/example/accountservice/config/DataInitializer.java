package com.example.accountservice.config;

import com.example.accountservice.model.Account;
import com.example.accountservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
            logger.info("Preloading H2 Database with sample accounts...");
            repository.save(new Account("1001", "Sudhir Tiwari", "USD", "New York"));
            repository.save(new Account("1002", "Akansha Jain", "EUR", "Berlin"));
            repository.save(new Account("1003", "Rahul Jain", "EUR", "Berlin"));
            logger.info("H2 Database seeding complete.");
        };
    };
}
