package pl.hackyeah.hackyeah24_backend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.hackyeah24_backend.models.Foundation;
import pl.hackyeah.hackyeah24_backend.requests.FoundationRequest;
import pl.hackyeah.hackyeah24_backend.responses.ResponseManager;
import pl.hackyeah.hackyeah24_backend.security.jwt.JwtUtils;
import pl.hackyeah.hackyeah24_backend.services.FoundationService;

@RestController
@RequestMapping("/foundations")
public class FoundationController extends CrudController<FoundationService,Foundation> {

    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public FoundationController(FoundationService service, PasswordEncoder encoder,
                                JwtUtils jwtUtils) {
        super(service);
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }
    @PostMapping("/register")
   public ResponseEntity<Foundation> create(@RequestBody @Valid FoundationRequest request) {
        return ResponseManager.ok(service.create(request));
   }

   @GetMapping("/{id}")
   public ResponseEntity<Foundation> findById(@PathVariable(name="id") int id){
        return ResponseManager.ok(service.findById(id));
   }
}
