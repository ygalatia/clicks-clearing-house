package service;

import domain.ClaimRequestStub;
import domain.model.ClearingHouseProcess;
import domain.model.Contract;
import domain.model.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.logging.Logger;

@Service
public class ClearingService {

    private static final Logger logger = Logger.getLogger(ClearingService.class.getName());
    @Autowired
    ProcessRepository processRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static String dataRouter_url= "http://localhost:8084/camel/clearinghouse";
    private static String getClaimByAccused_uri= "/getClaimByAccused?accusedId=";

    public Boolean clearTransaction(Contract contract){
        if(validateConnector(contract.getConsumer())){
            generateProcess(contract);
            return true;
        }
        else{
            return false;
        }
    }

    public String generateProcess(Contract contract){
        logger.info("Request Clearing House Process' generation");
        Optional<ClearingHouseProcess> process = getProcessByContractId(contract.getId());
        if (process.isEmpty()){
            logger.info("Generating new Clearing House Process");
            String generatedId = pidGenerator();
            processRepository.save(new ClearingHouseProcess(generatedId, contract.getId(), contract.getProvider(), contract.getConsumer()));
            logger.info("Clearing House Process is generated successfully");
            return generatedId;
        }
        else {
            String getProcessID = process.get().getProcessID();
            logger.info("Clearing House Process is already generated");
            return getProcessID;
        }
    }

    private Optional<ClearingHouseProcess> getProcessByContractId(String contractId) {
        return processRepository.findByContractID(contractId);
    }

    public ClearingHouseProcess getProcessbyId(String processID){
        Optional<ClearingHouseProcess> clearingHouseProcess = processRepository.findById(processID);
        if(!clearingHouseProcess.isEmpty()){
            logger.info("Fetching Clearing House Process");
            return clearingHouseProcess.get();
        }else {
            logger.info("Clearing House Process with id: " + processID + "does not exist in the record");
            return null;
        }
    }

    String pidGenerator(){
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    Boolean isContractValid(Contract contract){
        return false;
    }

    Boolean validateDate(String date){
        return false;
    }

    // TODO: DAPS?, Validate based on claim history of the connector
    Boolean validateConnector(String consumerId){
        if(findClaimByAccused(consumerId).isEmpty()){
            return true;
        } else {
            return false;
        }
    }

    List<ClaimRequestStub> findClaimByAccused(String accusedID){
        ResponseEntity<ClaimRequestStub[]> responseEntity = restTemplate.getForEntity(dataRouter_url + getClaimByAccused_uri + accusedID,ClaimRequestStub[].class);
        return Arrays.asList(responseEntity.getBody());
    }

    Boolean validateTransactionPayment(String paymentId){
        return false;
    }

}
