package controller;

import domain.context.SettlementContext;
import domain.model.LogBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping(path = "/settlement/settleTransaction")
    public LogBody settleTransaction(@RequestBody SettlementContext context, @RequestParam String processId) throws ParseException {
       return settlementService.settleTransaction(context, processId);
    }

}
