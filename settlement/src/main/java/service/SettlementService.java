package service;

import domain.ClearingHouseProcessStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SettlementService {

    @Autowired
    private RestTemplate restTemplate;

    private static String clearingService_url= "http://localhost:8080/clearing";
    private static String getProcessById_uri= "/getProcess?processID=";

    public ClearingHouseProcessStub getProcessById(String processID){
        ClearingHouseProcessStub clearingHouseProcessStub = restTemplate.getForObject(clearingService_url + getProcessById_uri + processID, ClearingHouseProcessStub.class);
        return clearingHouseProcessStub;
    }
    public boolean isTransactionDischarged(ClearingHouseProcessStub clearingHouseProcessStub) throws ParseException {
        boolean isDischarged = false;
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date endDate = sdf.parse(clearingHouseProcessStub.contractPayload().endDate());
        Date currentDate = new Date();
        if (endDate.after(currentDate)){
            isDischarged = true;
        }
        return isDischarged;
    }
}
