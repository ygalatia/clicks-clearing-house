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
                .post().to("direct:start-clearing-house")
                .get("/welcome").to("direct:welcome")
                .get("/getProcess").to("direct:get-clearinghouse-process")
                .post("/start-services").consumes("application/json").to("direct:clear-transaction")
                .get("/getClaimByAccused").to("direct:get-claim-by-accused")
                .post("/clear-transaction").to("direct:clear-transaction")
                .post("/settle-transaction").to("direct:settle-transaction")
                .post("/log-transaction").to("direct:log-transaction")
                .post("/file=claim").to("direct:file-claim");

        from("direct:hello")
                .log("Helloooow")
                .setBody(constant("Hello from Camel route!"));

        from("direct:welcome")
                .log("Redirecting to Clearing App")
                .to("http://localhost:8080/clearing?bridgeEndpoint=true")
                .log("Redirecting to Settlement App")
                .to("http://localhost:8081/settlement?bridgeEndpoint=true")
                .log("Redirecting to Logging App")
                .to("http://localhost:8082/logging?bridgeEndpoint=true")
                .log("Redirecting to Claim Handling App")
                .to("http://localhost:8083/claim-handling?bridgeEndpoint=true");

        from("direct:clear-transaction")
                .log("Start using Clearing House service")
                .log("Validating contract and generating PID")
                .to("http://localhost:8080/clearing/clearTransaction?bridgeEndpoint=true")
                .log("Transaction Cleared : ${body}");

        from("direct:settle-transaction")
                .log("Start settlement service")
                .log("Settling transaction for PID: ${header.processId}")
                .to("http://localhost:8081/settlement/settleTransaction?processId=${header.processId}&bridgeEndpoint=true")
                .log("Settlement history: ${body}")
                .to("direct:log-transaction");

        from("direct:log-transaction")
                .log("Logging transaction with PID: ")
                .to("http://localhost:8082/logging/recordTransaction?bridgeEndpoint=true")
                .log("Transaction logged");

        from("direct:get-clearinghouse-process")
                .log("Getting Clearing House Process with ID: ${header.processId}")
                .to("http://localhost:8080/clearing/getProcess?processId=${header.processId}&bridgeEndpoint=true")
                .log("Fetch ${body}");

        from("direct:file-claim")
                .log("Filing Claim")
                .to("http://localhost:8083/claim-handling/fileClaim?bridgeEndpoint=true")
                .log("Claim filed with status OPEN");

        from("direct:generate-report")
                .log("Generating report for Process ID: ${body}")
                .to("http://localhost:8082/logging/generateReport?bridgeEndpoint=true")
                .log("Report generated");

        from("direct:get-claim-by-accused")
                .log("Getting claim over fraud/violation history accused on: ${header.accusedId}")
                .to("http://localhost:8083/claim-handling/getByAccused?accusedId=${header.accusedId}&bridgeEndpoint=true")
                .log("${body.length} history record(s) found");

        from("direct:start-clearing-house")
                .routeId("start-clearing-house")
                .log("Listening to destination: ${header.dst}")
                .choice()
                .when(header("dst").contains("clearing"))
                    .to("direct:clear-transaction")
                .when(header("dst").contains("settlement"))
                    .to("direct:settle-transaction")
                .when(header("dst").contains("report"))
                    .to("direct:generate-report")
                .when(header("dst").contains("claim"))
                    .to("direct:file-claim")
                .otherwise()
                    .setBody(constant("Please specify the destination: clearing, settlement, report, or claim"));


    }
}
