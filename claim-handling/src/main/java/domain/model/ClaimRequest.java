package domain.model;

import domain.TransactionLogStub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("claim_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimRequest {
    @Id
    String claimRequestID;
    String claimOwnerID;
    String claimDestinationID;
    String claimDescription;
    String claimDate;
    ClaimRequestStatus status;
    TransactionLogStub logPayload;
}

