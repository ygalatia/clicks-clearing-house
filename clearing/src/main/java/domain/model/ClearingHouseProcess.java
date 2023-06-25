package domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

@Document("clearinghouse_process")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClearingHouseProcess {
    @Id
    String processID;
    String processOwner;
    Contract contractPayload;
}
