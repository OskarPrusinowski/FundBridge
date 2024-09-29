package pl.hackyeah.hackyeah24_backend.services;

import org.springframework.stereotype.Service;
import pl.hackyeah.hackyeah24_backend.models.Tag;
import pl.hackyeah.hackyeah24_backend.repositories.TagRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService extends CrudService<TagRepository, Tag> {
    public TagService(TagRepository repository) {
        super(repository);
    }

    public Set<Tag> findByIds(List<Integer> ids){
        Collection<Tag> tags =  repository.findAllById(ids);
        return tags.stream()
                .collect(Collectors.toSet());
    }
}
