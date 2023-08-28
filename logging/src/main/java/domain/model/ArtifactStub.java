package domain.model;

public record ArtifactStub(String uuid, String remoteid,
                           String title, String accessURL, Boolean automatedDownload,
                           String username, String password, String remoteAddress,
                           Integer numAccessed, Integer byteSize, String checkSum,
                           String artifactJSON, String apiKey, String routingType) {
}
