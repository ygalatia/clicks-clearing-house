package domain.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClaimRequestRepository extends MongoRepository<ClaimRequest, String > {

    Optional<ClaimRequest> findById(String claimRequestID);
}
