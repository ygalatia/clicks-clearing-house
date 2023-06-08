package controller;

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

    @PostMapping(path="/clearing/readContract")
    public Contract readContract(@RequestBody Contract contract){
       return contract;
    }

    @PostMapping(path="/clearing/generatePid")
    public Long generateProcessID(@RequestBody Contract contract){
        Boolean isCleared = false;
        if (contract.getIsValid()){
            System.out.println("Transaction cleared");
            isCleared = true;
        }
        else{
            System.out.println("Transaction cannot be cleared");
        }

        return clearingService.generateProcess(true, contract.getStartDate(), contract.getEndDate(), contract).getProcessID();
    }
}
