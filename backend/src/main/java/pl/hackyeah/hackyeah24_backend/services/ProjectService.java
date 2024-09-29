package pl.hackyeah.hackyeah24_backend.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.hackyeah.hackyeah24_backend.models.Project;
import pl.hackyeah.hackyeah24_backend.repositories.CrudRepository;
import pl.hackyeah.hackyeah24_backend.repositories.ProjectRepository;
import pl.hackyeah.hackyeah24_backend.requests.ProjectRequest;
@Service
public class ProjectService extends CrudService<ProjectRepository,Project> {

    public FoundationService foundationService;

    public ProjectService(ProjectRepository repository, FoundationService foundationService) {
        super(repository);
        this.foundationService=foundationService;
    }

    @Transactional
    public Project create(ProjectRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setShort_about_description(request.getShort_about_description());
        project.setLong_about_description(request.getLong_about_description());
        project.setImage_id(request.getImage_id());
        project.setHow_description(request.getHow_description());
        project.setNeeds_description(request.getNeeds_description());
        project.setFoundation(foundationService.findById(request.getFoundation_id()));
        return repository.save(project);
    }


}
