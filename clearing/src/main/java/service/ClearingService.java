package service;

import domain.model.ClearingHouseProcess;
import domain.model.Contract;
import domain.model.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;

@Service
public class ClearingService {

    @Autowired
    ProcessRepository processRepository;

    public String generateProcess(String ownerID, Contract contractPayload){
        processRepository.save(new ClearingHouseProcess("proc123", ownerID, contractPayload));
        return "success creating process";
    }


}
