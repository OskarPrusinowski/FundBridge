package pl.hackyeah.hackyeah24_backend.services;

import org.springframework.stereotype.Service;
import pl.hackyeah.hackyeah24_backend.models.Sector;
import pl.hackyeah.hackyeah24_backend.repositories.SectorRepository;

@Service
public class SectorService extends CrudService<SectorRepository,Sector> {
    public SectorService(SectorRepository repository) {
        super(repository);
    }
}
