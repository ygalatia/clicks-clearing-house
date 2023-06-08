package controller;

import domain.ContractStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.ClearingService;

@RestController
public class ClearingController {

    @Autowired
    ClearingService clearingService;

    @PostMapping(path="/clearing/readcontract")
    public String readContract(@RequestBody ContractStub contractStub){
       return clearingService.readContract(contractStub);
    }
}
