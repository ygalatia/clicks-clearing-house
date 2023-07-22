package main;

import domain.model.ProcessRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"config, controller, domain, service"})
@EnableMongoRepositories(basePackageClasses = ProcessRepository.class)
public class ClearingServiceApplication {
        public static void main(String[] args) {
            SpringApplication.run(ClearingServiceApplication.class, args);}
}
