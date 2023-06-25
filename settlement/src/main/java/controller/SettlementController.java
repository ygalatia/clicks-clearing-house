package controller;

import domain.ClearingHouseProcessStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String settleTransaction(@RequestParam String processID) throws ParseException {
        //TODO: Update settlement logics
        ClearingHouseProcessStub clearingHouseProcessStub = settlementService.getProcessById(processID);
        if(clearingHouseProcessStub != null) {
            String message = "";
            if (settlementService.isTransactionDischarged(clearingHouseProcessStub)) {
                //TODO: Proceed Transaction
                message = "Transaction Settled";
            } else {
                //TODO: Cancel/Deny transaction
                message = "Transaction discharged";
            }
            return message;
        } else{
            return "Process Does Not Exist";
        }
    }

}
