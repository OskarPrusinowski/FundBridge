package pl.hackyeah.hackyeah24_backend.controllers;


import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.hackyeah.hackyeah24_backend.models.BaseModel;
import pl.hackyeah.hackyeah24_backend.responses.ResponseManager;
import pl.hackyeah.hackyeah24_backend.services.CrudService;

import java.util.List;

@RestController
@RequestMapping
@Hidden
public abstract class CrudController <T extends CrudService,S extends BaseModel>  {

    protected T service;
    @Autowired
    public CrudController(T service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<S>> findAll(){
        return ResponseManager.ok(service.findAll());
    }

//   @PostMapping
//   public ResponseEntity<S> create(@RequestBody @Valid S s) {
//       return ResponseManager.ok((S) service.create(s));
//   }

    @PutMapping("/{id}")
    public ResponseEntity<S> update(@RequestBody @Valid S s,@PathVariable int id) {
        return ResponseManager.ok((S) service.update(s,id));
    }

}
