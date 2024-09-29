package pl.hackyeah.hackyeah24_backend.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hackyeah.hackyeah24_backend.models.Foundation;
import pl.hackyeah.hackyeah24_backend.models.Sector;
import pl.hackyeah.hackyeah24_backend.responses.ResponseManager;
import pl.hackyeah.hackyeah24_backend.services.SectorService;
@RestController
@RequestMapping("/sectors")
public class SectorController extends CrudController<SectorService, Sector> {
    public SectorController(SectorService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity<Sector> create(@RequestBody @Valid Sector sector) {
        return ResponseManager.ok(service.create(sector));
    }
}
