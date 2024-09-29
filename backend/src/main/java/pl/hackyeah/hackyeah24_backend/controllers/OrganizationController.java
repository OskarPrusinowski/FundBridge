package pl.hackyeah.hackyeah24_backend.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.hackyeah.hackyeah24_backend.models.Organization;
import pl.hackyeah.hackyeah24_backend.repositories.OrganizationRepository;
import pl.hackyeah.hackyeah24_backend.services.OrganizationService;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private OrganizationService <OrganizationRepository, Organization> service;

    public OrganizationController(OrganizationService<OrganizationRepository, Organization> service) {
        this.service = service;
    }

    @PostMapping("/login")
    public Organization login(@RequestParam(name = "email") String email,
                              @RequestParam(name="password") String password) throws Throwable {
        return service.login(email,password);
    }
}
