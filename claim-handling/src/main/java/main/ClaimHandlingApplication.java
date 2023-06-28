package main;

import domain.model.ClaimRequestRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"controller, domain, service"})
@EnableMongoRepositories(basePackageClasses = ClaimRequestRepository.class)
public class ClaimHandlingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClaimHandlingApplication.class, args);}
}
