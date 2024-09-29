package pl.hackyeah.hackyeah24_backend.repositories;

import org.springframework.stereotype.Repository;
import pl.hackyeah.hackyeah24_backend.models.FileInfo;

import java.util.Optional;
@Repository
public interface FileInfoRepository extends CrudRepository<FileInfo> {


    Optional<FileInfo> findFirstByPath(String path);

}
