package service;

import domain.TransactionLogStub;
import domain.model.ClaimRequest;
import domain.model.ClaimRequestRepository;
import domain.model.ClaimRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaimHandlingService {

    @Autowired
    ClaimRequestRepository claimRequestRepository;

    public ClaimRequest storeClaimRequest(TransactionLogStub transactionLogStub, String claimOwnerID, String claimDestinationID){
        return claimRequestRepository.save(new ClaimRequest("claim123", claimOwnerID, claimDestinationID, "Contract violation", "28-06-2023", ClaimRequestStatus.OPEN, transactionLogStub));
    }

    //TODO: Find A Way to update the status in one service
    public String approveClaim(String claimRequestID){
        ClaimRequest claimRequest = claimRequestRepository.findById(claimRequestID).get();
        claimRequest.setStatus(ClaimRequestStatus.APPROVED);
        claimRequestRepository.save(claimRequest);

        return "Claim Approved";
    }

    public String rejectClaim(String claimRequestID){
        ClaimRequest claimRequest = claimRequestRepository.findById(claimRequestID).get();
        claimRequest.setStatus(ClaimRequestStatus.REJECTED);
        claimRequestRepository.save(claimRequest);

        return "Claim Rejected";
    }

    public String reviewClaim(String claimRequestID){
        ClaimRequest claimRequest = claimRequestRepository.findById(claimRequestID).get();
        claimRequest.setStatus(ClaimRequestStatus.REVIEW);
        claimRequestRepository.save(claimRequest);

        return "Claim Reviewed";
    }

}
