package service;

import domain.ClearingHouseProcessStub;
import domain.context.SettlementContext;
import domain.model.Artifact;
import domain.model.LogBody;
import domain.model.UsagePolicy;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

@Service
public class SettlementService {

    @Autowired
    private RestTemplate restTemplate;

    private LogBody logBody;

    private static String dataRouter_url= "http://localhost:8084/camel/clearinghouse";
    private static String getProcessById_uri= "/getProcess?processId=";
    private static String logTransaction_uri= "/logTransaction";

    public ClearingHouseProcessStub getProcessById(String processID){
        ClearingHouseProcessStub clearingHouseProcessStub = restTemplate.getForObject(dataRouter_url + getProcessById_uri + processID, ClearingHouseProcessStub.class);
        return clearingHouseProcessStub;
    }

    public LogBody settleTransaction(SettlementContext context, String processId){
        Artifact artifact = context.getArtifact();
        UsagePolicy usagePolicy = context.getUsagePolicy();

        //Should be by processID
        ClearingHouseProcessStub clearingHouseProcessStub = getProcessById(processId);

        logBody = new LogBody(generateLogId(), clearingHouseProcessStub.id(), "xxx", LocalDateTime.now().toString(), false, false, false, false, "", artifact, null);
        if(isTransactionValid(artifact.getNumAccessed(), usagePolicy.getTimesUsage(),usagePolicy.getIntervalStart(), usagePolicy.getIntervalEnd(), logBody)){
            logBody.setConsumerReachable(true);
            logBody.setProviderReachable(true);
            logBody.setArtifactAfter(artifact);
            logBody.setRemarks("Transaction settlement succeed on: " + LocalDateTime.now().toString());
            //invokeLogging(logBody);
        } else {
            logBody.setRemarks("Transaction settlement failed on: " + LocalDateTime.now().toString());
        }
        return logBody;
    }
    public boolean isTransactionDischarged(ClearingHouseProcessStub clearingHouseProcessStub) throws ParseException {
        //TODO: Add user validation, only valid/authorised user able to make process
        boolean isDischarged = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date endDate = sdf.parse(clearingHouseProcessStub.endDate());
        Date currentDate = new Date();
        if (endDate.after(currentDate)) {
            isDischarged = true;
        }
        return isDischarged;
    }

    private Boolean isTransactionValid(Integer artifactNumAccess, Integer policyTimesUsage, String startDate, String endDate, LogBody logBody){
        if(validateTimesUsage(artifactNumAccess, policyTimesUsage, logBody) && validateAccessDate(startDate, endDate, logBody)){
            return true;
        } else {
            return false;
        }
    }

    private Boolean validateTimesUsage(Integer artifactNumAccess, Integer policyTimeUsage, LogBody logBody){
        if(artifactNumAccess <= policyTimeUsage){
            logBody.setNumAccessValid(true);
            return true;
        } else {
            return false;
        }
    }

    private Boolean validateAccessDate(String startDate, String endDate, LogBody logBody) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date currentDate = new Date();

            Date start = sdf.parse(startDate);
            Date end = sdf.parse(endDate);

            if(currentDate.after(start) && currentDate.before(end)){
                logBody.setDateAccessValid(true);
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    // Check connectors availability during invocation of the settlement service
    private Boolean isConnectorReachable(String connectorID){
        //TODO: Find a way to check if connector reachable/available
        return false;
    }

    private Boolean isArtifactExist(){
        return false;
    }

    private String generateLogId(){
        return UUID.randomUUID().toString();
    }

    private void invokeLogging(LogBody logBody){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LogBody> request = new HttpEntity<>(logBody, headers);
        restTemplate.postForObject(dataRouter_url + logTransaction_uri, request, LogBody.class);
    }
}
