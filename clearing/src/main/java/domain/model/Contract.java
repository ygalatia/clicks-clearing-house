package domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contract {

    long contractId;
    Boolean isValid;
    String startDate;
    String endDate;

    public Contract(long contractId, Boolean isValid, String startDate, String endDate) {
        this.contractId = contractId;
        this.isValid = isValid;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
