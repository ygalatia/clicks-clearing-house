package routes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CoreConnectorRoutesManager extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // Define your integration routes here using Apache Camel DSL
        rest("/clearinghouse")
                .get("/welcome").to("direct:welcome")
                .post("/start-services").consumes("application/json").to("direct:clear-transaction");

        from("direct:hello")
                .log("Helloooow")
                .setBody(constant("Hello from Camel route!"));

        from("direct:welcome")
                .log("Redirecting to Clearing App")
                .to("http://localhost:8080/clearing?bridgeEndpoint=true")
                .log("Redirecting to Settlement App")
                .to("http://localhost:8081/settlement?bridgeEndpoint=true")
                .log("Redirecting to Logging App")
                .to("http://localhost:8082/logging?bridgeEndpoint=true");

        from("direct:clear-transaction")
                .log("Start using Clearing House service")
                .log("Validating contract and generating PID")
                .to("http://localhost:8080/clearing/generatePid?bridgeEndpoint=true")
                .log("PID Generated with ID: ${body}");

        from("direct:settle-transaction")
                .log("Start settlement service")
                .log("Settling transaction")
                .to("http://localhost:8081/settlement/settleTransaction")
                .log("Settlement Response: ")
                .to("direct:log-transaction");

        from("direct:log-transaction")
                .log("Logging transaction with PID: ")
                .to("http://localhost:8082/logging/recordTransaction")
                .log("Transaction logged");

    }
}
