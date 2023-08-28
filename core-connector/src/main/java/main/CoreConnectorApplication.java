package main;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import routes.CoreConnectorRoutesManager;

@SpringBootApplication
@ComponentScan({"routes"})
public class CoreConnectorApplication {

//    @Autowired
//    private CoreConnectorRoutesManager coreConnectorRoutesManager;

    public static void main(String[] args) {
        SpringApplication.run(CoreConnectorApplication.class, args);}

//    @PostConstruct
//    public void init() throws Exception {
//        // Start integration routes when the application starts
//        coreConnectorRoutesManager.start();
//    }
}
