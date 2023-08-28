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
    String id;
    String Resourceid;
    String title;
    String startDate;
    String endDate;
    String consumer;
    String provider;
}
