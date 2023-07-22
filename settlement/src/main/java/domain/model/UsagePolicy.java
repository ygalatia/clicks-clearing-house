package domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsagePolicy {
    String uuid;
    String title;
    String value;
    String pattern;
    Integer timesUsage;
    String intervalStart;
    String intervalEnd;
    Integer usageDuration;
}
