package domain;

public record ClaimRequestStub( String claimRequestID, String claimantId,
                                String accusedId, String claimDescription,
                                String claimDate, String status,
                                String transactionLogId) {
}
