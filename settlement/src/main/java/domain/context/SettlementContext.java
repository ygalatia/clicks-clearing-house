package domain.context;

import domain.ClearingHouseProcessStub;
import domain.model.Artifact;
import domain.model.UsagePolicy;
import lombok.Getter;

@Getter
public class SettlementContext {
    private Artifact artifact;
    private UsagePolicy usagePolicy;
}
