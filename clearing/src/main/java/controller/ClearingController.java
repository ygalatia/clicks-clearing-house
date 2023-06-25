package controller;

import domain.model.ClearingHouseProcess;
import domain.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ClearingService;

@RestController
public class ClearingController {

    @Autowired
    ClearingService clearingService;

    @GetMapping(path="/clearing")
    public String clearing(){
        return "The Clearing Service";
    }

    @GetMapping(path="/clearing/getProcess")
    public ClearingHouseProcess getProcess(){
        Contract dummy = new Contract("abc123", "connector123",true, "07-06-2023", "10-06-2023");
        return new ClearingHouseProcess("abc123", dummy.getProviderId(), dummy);
    }

    @PostMapping(path="/clearing/readContract")
    public Contract readContract(@RequestBody Contract contract){
       return contract;
    }

    @PostMapping(path="/clearing/generatePid")
    public String generateProcessID(@RequestBody Contract contract){
        Boolean isCleared = false;
        if (contract.getIsValid()){
            System.out.println("Transaction cleared");
            isCleared = true;
        }
        else{
            System.out.println("Transaction cannot be cleared");
        }

        return clearingService.generateProcess(contract.getProviderId(), contract);
    }
}
