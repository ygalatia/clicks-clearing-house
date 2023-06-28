package controller;

import domain.TransactionLogStub;
import domain.model.ClaimRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ClaimHandlingService;

@RestController
public class ClaimHandlingController {

    @Autowired
    ClaimHandlingService claimHandlingService;

    @GetMapping(path = "/claim-handling/")
    public String claimHandling(){
        return "The Claim Handling service";
    }

    @PostMapping(path = "claim-handling/fileClaim")
    public ClaimRequest fileClaimRequest(@RequestBody TransactionLogStub transactionLogStub, @RequestParam String claimOwnerId, @RequestParam String claimDestinationID){
        return claimHandlingService.storeClaimRequest(transactionLogStub, claimOwnerId, claimDestinationID);
    }

    @PostMapping(path = "claim-handling/approveClaim")
    public String validateClaim(@RequestParam String claimRequestID){
        return claimHandlingService.approveClaim(claimRequestID);
    }
}
