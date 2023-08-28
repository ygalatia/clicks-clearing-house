package controller;

import domain.TransactionLogStub;
import domain.model.ClaimRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ClaimHandlingService;

import java.util.List;

@RestController
public class ClaimHandlingController {

    @Autowired
    ClaimHandlingService claimHandlingService;

    @GetMapping(path = "/claim-handling")
    public String claimHandling(){
        return "The Claim Handling service";
    }

    @PostMapping(path = "claim-handling/fileClaim")
    public ClaimRequest fileClaimRequest(@RequestParam String claimantId, @RequestParam String accusedId, @RequestParam String description, @RequestParam String transactionLogId){
        return claimHandlingService.storeClaimRequest(claimantId, accusedId, description, transactionLogId);
    }

    @GetMapping(path = "claim-handling/getByAccused")
    public List<ClaimRequest> getByAccusedId(@RequestParam String accusedId){
        return claimHandlingService.getClaimByAccused(accusedId);
    }

    @PostMapping(path = "claim-handling/approveClaim")
    public String validateClaim(@RequestParam String claimRequestID){
        return claimHandlingService.approveClaim(claimRequestID);
    }

    @PostMapping(path = "claim-handling/rejectClaim")
    public String rejectClaim(@RequestParam String claimRequestID){
        return claimHandlingService.rejectClaim(claimRequestID);
    }

    @PostMapping(path = "claim-handling/reviewClaim")
    public String reviewClaim(@RequestParam String claimRequestID){
        return claimHandlingService.reviewClaim(claimRequestID);
    }

    @GetMapping(path = "claim-handling/getClaim")
    public ClaimRequest getClaimByID(@RequestParam String claimRequestID){
        return claimHandlingService.getClaimByID(claimRequestID);
    }
}
