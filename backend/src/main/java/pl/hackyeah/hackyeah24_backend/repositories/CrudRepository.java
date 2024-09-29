package pl.hackyeah.hackyeah24_backend.repositories;


import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.hackyeah.hackyeah24_backend.models.BaseModel;

@Repository
public interface CrudRepository<T extends BaseModel> extends JpaRepository<T,Integer> {
}
