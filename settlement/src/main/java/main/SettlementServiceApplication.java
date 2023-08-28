package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"config, controller, domain, service"})
public class SettlementServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(SettlementServiceApplication.class, args);
    }
}
