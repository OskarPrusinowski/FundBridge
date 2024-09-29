package pl.hackyeah.hackyeah24_backend.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.hackyeah.hackyeah24_backend.exceptions.rest.RestNotFoundException;
import pl.hackyeah.hackyeah24_backend.models.Organization;
import pl.hackyeah.hackyeah24_backend.repositories.OrganizationRepository;
import pl.hackyeah.hackyeah24_backend.responses.JwtResponse;

@Service
@Primary
public class OrganizationService <T extends OrganizationRepository,S extends Organization> extends CrudService<T,S> {

    public TagService tagService;
    public OrganizationService(T repository,TagService tagService) {
        super(repository);
        this.tagService=tagService;
    }

    public Organization login(String email, String password) throws Throwable {
        Organization organization = (Organization) repository.findByEmail(email).orElseThrow(()->new RestNotFoundException("Not found by email "+email));
        return organization;
    }
}
