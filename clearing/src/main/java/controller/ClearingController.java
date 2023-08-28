package controller;

import domain.model.ClearingHouseProcess;
import domain.model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public ClearingHouseProcess getProcess(@RequestParam String processId){
        return clearingService.getProcessbyId(processId);
    }

    @PostMapping(path="/clearing/readContract")
    public Contract readContract(@RequestBody Contract contract){
       return contract;
    }

    @PostMapping(path="/clearing/clearTransaction")
    public Boolean clearTransaction(@RequestBody Contract contract){
        return clearingService.clearTransaction(contract);
    }
}
