package domain.model;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProcessRepository extends MongoRepository<ClearingHouseProcess, String> {

    Optional<ClearingHouseProcess> findById(String processId);
    List<ClearingHouseProcess> findAll();

    Optional<ClearingHouseProcess> findByContractID(String contractID);

}
