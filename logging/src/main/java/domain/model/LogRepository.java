package domain.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LogRepository extends MongoRepository<Log, String> {

    Optional<Log> findById(String logId);
    List<Log> findByClearingHouseProcessId(String processId);
}
