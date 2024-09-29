package pl.hackyeah.hackyeah24_backend.repositories;

import org.springframework.stereotype.Repository;
import pl.hackyeah.hackyeah24_backend.models.Tag;

@Repository
public interface TagRepository extends CrudRepository<Tag> {
}
