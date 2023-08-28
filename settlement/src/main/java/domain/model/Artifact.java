package domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Artifact {
    String uuid;
    String remoteid;
    String title;
    String accessURL;
    Boolean automatedDownload;
    String username;
    String password;
    String remoteAddress;
    Integer numAccessed;
    Integer byteSize;
    String checkSum;
    String artifactJSON;
    String apiKey;
    String routingType;
}
