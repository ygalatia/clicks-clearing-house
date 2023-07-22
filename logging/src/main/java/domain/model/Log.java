package domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("transaction_log")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    String logId;
    String clearingHouseProcessId;
    String logOwnerId;
    String accessDateTime;
    Boolean dateAccessValid;
    Boolean numAccessValid;
    Boolean providerReachable;
    Boolean consumerReachable;
    String remarks;
    ArtifactStub artifactBefore;
    ArtifactStub artifactAfter;
}
