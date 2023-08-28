package domain;

public record TransactionMetadataStub(String transactionId, String transactionOwnerId,
                                      String contractId, String description,
                                      String date, String metadata) {
}
