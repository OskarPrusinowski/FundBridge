package pl.hackyeah.hackyeah24_backend.repositories;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.hackyeah.hackyeah24_backend.models.Organization;

import java.util.Optional;

@Repository
@Primary
public interface OrganizationRepository <S extends Organization> extends CrudRepository<S>{

    @Query("SELECT o FROM Organization o WHERE o.email =:email")
    Optional<Organization> findByEmail(String email);
}
