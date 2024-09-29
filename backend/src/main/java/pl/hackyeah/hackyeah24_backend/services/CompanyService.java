package pl.hackyeah.hackyeah24_backend.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.hackyeah.hackyeah24_backend.models.Company;
import pl.hackyeah.hackyeah24_backend.repositories.CompanyRepository;
import pl.hackyeah.hackyeah24_backend.repositories.SectorRepository;
import pl.hackyeah.hackyeah24_backend.requests.CompanyRequest;

@Service
public class CompanyService extends OrganizationService<CompanyRepository, Company> {
    private final SectorService sectorService;

    public CompanyService(CompanyRepository repository,TagService tagService, SectorService sectorService) {

        super(repository, tagService);
        this.sectorService = sectorService;
    }

    @Transactional
    public Company create(CompanyRequest request) {
        Company company=new Company();
        company.setName(request.getName());
        company.setEmail(request.getEmail());
        company.setDescription(request.getDescription());
        company.setMaxBudget(request.getMaxBudget());
        company.setMinBudget(request.getMinBudget());
        company.setNip(request.getNip());
        company.setSector(sectorService.findById(request.getSector_id()));
        company.setOpenToWork(request.isOpenToWork());
        company.setSize(request.getSize());
        company.setVerified(request.isVerified());
        company.setWebsiteUrl(request.getWebsiteUrl());
        company.setPassword(request.getPassword());

        company.setTags(tagService.findByIds(request.getTagsIds()));

        return repository.save(company);
    }
}
