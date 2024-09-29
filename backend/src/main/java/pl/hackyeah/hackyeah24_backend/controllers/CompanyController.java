package pl.hackyeah.hackyeah24_backend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hackyeah.hackyeah24_backend.models.Company;
import pl.hackyeah.hackyeah24_backend.requests.CompanyRegisterRequest;
import pl.hackyeah.hackyeah24_backend.requests.CompanyRequest;
import pl.hackyeah.hackyeah24_backend.responses.ResponseManager;
import pl.hackyeah.hackyeah24_backend.security.jwt.JwtUtils;
import pl.hackyeah.hackyeah24_backend.services.CompanyService;
import pl.hackyeah.hackyeah24_backend.services.OrganizationService;

@RestController
@RequestMapping("/companies")
public class CompanyController extends CrudController<CompanyService,Company> {

    private final CompanyService companyService;
    private final OrganizationService organizationService;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;


    @Autowired
    public CompanyController(CompanyService service,
                             OrganizationService organizationService,
                             PasswordEncoder encoder,
                             JwtUtils jwtUtils) {
        super(service);
        this.companyService = service;
        this.organizationService = organizationService;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }


    @PostMapping("/register")
    public ResponseEntity<Company> create(@RequestBody @Valid CompanyRequest company) {
        return ResponseManager.ok(service.create(company));
    }
}
