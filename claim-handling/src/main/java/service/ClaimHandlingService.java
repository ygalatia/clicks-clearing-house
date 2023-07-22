package service;

import domain.TransactionLogStub;
import domain.model.ClaimRequest;
import domain.model.ClaimRequestRepository;
import domain.model.ClaimRequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ClaimHandlingService {

    @Autowired
    ClaimRequestRepository claimRequestRepository;

    public ClaimRequest storeClaimRequest(String claimantId, String accusedId, String description, String transactionLogId){
        return claimRequestRepository.save(new ClaimRequest(UUID.randomUUID().toString(), claimantId, accusedId, description, LocalDateTime.now().toString(), "Open", transactionLogId));
    }

    public List<ClaimRequest> getClaimByAccused(String accusedId){
        return claimRequestRepository.findByAccusedId(accusedId);
    }

    //TODO: Find A Way to update the status in one service
    public String approveClaim(String claimRequestID){
        ClaimRequest claimRequest = claimRequestRepository.findById(claimRequestID).get();
        claimRequest.setStatus("approved");
        claimRequestRepository.save(claimRequest);

        return "Claim Approved";
    }

    public String rejectClaim(String claimRequestID){
        ClaimRequest claimRequest = claimRequestRepository.findById(claimRequestID).get();
        claimRequest.setStatus("rejected");
        claimRequestRepository.save(claimRequest);

        return "Claim Rejected";
    }

    public String reviewClaim(String claimRequestID){
        ClaimRequest claimRequest = claimRequestRepository.findById(claimRequestID).get();
        claimRequest.setStatus("review");
        claimRequestRepository.save(claimRequest);

        return "Claim Reviewed";
    }

    public ClaimRequest getClaimByID(String claimRequestID){
        return claimRequestRepository.findById(claimRequestID).get();
    }

}
