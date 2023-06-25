package domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    String contractId;
    String providerId;
    Boolean isValid;
    String startDate;
    String endDate;
}
