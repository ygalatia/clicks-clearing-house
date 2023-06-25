package service;

import domain.model.ClearingHouseProcess;
import domain.model.Contract;
import domain.model.ProcessRepository;
import main.ClearingServiceApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

import java.util.logging.LogManager;
import java.util.logging.Logger;

@Service
public class ClearingService {

    private static final Logger logger = Logger.getLogger(ClearingService.class.getName());
    @Autowired
    ProcessRepository processRepository;

    public String generateProcess(Contract contractPayload){
        //TODO: check if process already exist
        logger.info("Request Clearing House Process generation");
        String generatedId = pidGenerator(contractPayload);
        if (!isProcessExist(generatedId)){
            logger.info("Generating new Clearing House Process");
            processRepository.save(new ClearingHouseProcess(generatedId, contractPayload.getProviderId(), contractPayload));
        }
        logger.info("Clearing House Process is/already generated");
        return generatedId;
    }

    String pidGenerator(Contract contract){
        return contract.getProviderId()+"CH_PID"+contract.getContractId();
    }

    Boolean isProcessExist(String generatedId){
        if (processRepository.existsById(generatedId)){
            logger.info("Clearing House Process exist in the record");
            return true;
        }
        else {
            logger.info("Clearing House Process does not exist in the record");
            return false;
        }
    }

}
