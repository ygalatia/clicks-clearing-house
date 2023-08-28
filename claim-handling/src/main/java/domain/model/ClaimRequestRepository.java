package domain.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaimRequestRepository extends MongoRepository<ClaimRequest, String > {

    Optional<ClaimRequest> findById(String claimRequestId);

    List<ClaimRequest> findByAccusedId(String accusedId);
}
