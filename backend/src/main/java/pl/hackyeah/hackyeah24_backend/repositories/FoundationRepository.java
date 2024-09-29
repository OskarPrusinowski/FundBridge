package pl.hackyeah.hackyeah24_backend.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.hackyeah.hackyeah24_backend.models.Foundation;

import java.util.List;

@Repository
public interface FoundationRepository extends OrganizationRepository<Foundation> {
    @Query(value = "SELECT f FROM Foundation f")
    List<Foundation> findLimitedFoundations(Pageable pageable);
}
