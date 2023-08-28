package domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogBody {
    String logId;
    String clearingHouseProcessId;
    String logOwnerId;
    String accessDateTime;
    Boolean dateAccessValid;
    Boolean numAccessValid;
    Boolean providerReachable;
    Boolean consumerReachable;
    String remarks;
    Artifact artifactBefore;
    Artifact artifactAfter;
}
