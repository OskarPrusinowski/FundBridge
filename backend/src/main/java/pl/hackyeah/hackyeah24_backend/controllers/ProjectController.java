package pl.hackyeah.hackyeah24_backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.hackyeah24_backend.models.Project;
import pl.hackyeah.hackyeah24_backend.requests.ProjectRequest;
import pl.hackyeah.hackyeah24_backend.responses.ResponseManager;
import pl.hackyeah.hackyeah24_backend.services.FoundationService;
import pl.hackyeah.hackyeah24_backend.services.ProjectService;

@RequestMapping("/projects")
@RestController
public class ProjectController extends CrudController<ProjectService,Project> {
    public ProjectController(ProjectService service) {
        super(service);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable(name = "id") int id){
        return ResponseManager.ok(service.findById(id));
    }
    @PostMapping
    public ResponseEntity<Project> create(@RequestBody ProjectRequest request) {
        return ResponseEntity.ok(service.create(request));
    }
}
