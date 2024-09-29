package pl.hackyeah.hackyeah24_backend.repositories;

import org.springframework.stereotype.Repository;
import pl.hackyeah.hackyeah24_backend.models.Company;

@Repository
public interface CompanyRepository extends OrganizationRepository<Company> {
}
