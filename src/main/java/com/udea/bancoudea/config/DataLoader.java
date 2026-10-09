package com.udea.bancoudea.config;

import com.udea.bancoudea.entity.Customer;
import com.udea.bancoudea.repository.CustomerRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadCustomers(CustomerRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Customer(null, "1001", "Ana", "Gomez", 1000000.0));
                repository.save(new Customer(null, "1002", "Luis", "Perez", 500000.0));
            }
        };
    }
}
