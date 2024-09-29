package pl.hackyeah.hackyeah24_backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.hackyeah.hackyeah24_backend.models.Tag;
import pl.hackyeah.hackyeah24_backend.services.TagService;

@RestController
@RequestMapping("/tags")
public class TagController extends CrudController<TagService, Tag> {
    public TagController(TagService service) {
        super(service);
    }
}
