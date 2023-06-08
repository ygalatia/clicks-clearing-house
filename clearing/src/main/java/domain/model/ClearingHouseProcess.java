package domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClearingHouseProcess {
    long processID;

    Boolean isCleared;
    String startDate;
    String endDate;
    Contract payload;

    public ClearingHouseProcess(long processID, Boolean isCleared, String startDate, String endDate, Contract payload) {
        this.processID = processID;
        this.isCleared = isCleared;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payload = payload;
    }
}
