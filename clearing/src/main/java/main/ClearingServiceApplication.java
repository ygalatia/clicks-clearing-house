package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"controller, domain, service"})
public class ClearingApplication {
        public static void main(String[] args) {
            SpringApplication.run(ClearingApplication.class, args);}
}
