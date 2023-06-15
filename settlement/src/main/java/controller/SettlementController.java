package controller;

import domain.ClearingHouseProcessStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.SettlementService;

import java.text.ParseException;

@RestController
public class SettlementController {

    @Autowired
    SettlementService settlementService;

    @GetMapping(path = "/settlement")
    public String settlement(){
        return "The Settlement Service";
    }

    @GetMapping(path = "/settlement/settleTransaction")
    public String settleTransaction(@RequestBody ClearingHouseProcessStub clearingHouseProcessStub) throws ParseException {
        String message = "";
        if(settlementService.isTransactionDischarged(clearingHouseProcessStub)){
            message = "Transaction Settled";
        }
        else {
            message = "Transaction discharged";
        }

        return message;
    }

}
