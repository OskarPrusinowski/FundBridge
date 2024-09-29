package pl.hackyeah.hackyeah24_backend.services;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.hackyeah.hackyeah24_backend.models.Company;
import pl.hackyeah.hackyeah24_backend.models.Foundation;
import pl.hackyeah.hackyeah24_backend.repositories.FoundationRepository;
import pl.hackyeah.hackyeah24_backend.requests.CompanyRequest;
import pl.hackyeah.hackyeah24_backend.requests.FoundationRequest;

import java.util.List;

@Service
public class FoundationService extends OrganizationService<FoundationRepository, Foundation> {
    public SectorService sectorService;
    public FoundationService(FoundationRepository repository,TagService tagService, SectorService sectorService) {
        super(repository, tagService);
        this.sectorService=sectorService;
    }

    @Transactional
    public Foundation create(FoundationRequest request) {
        Foundation foundation=new Foundation();
        foundation.setName(request.getName());
        foundation.setEmail(request.getEmail());
        foundation.setAdditionalInfo(request.getAdditionalInfo());
        foundation.setExperience(request.getExperience());
        foundation.setGoals(request.getGoals());
        foundation.setStrengths(request.getStrengths());
        foundation.setHistory(request.getHistory());
        foundation.setNip(request.getNip());
        foundation.setSector(sectorService.findById(request.getSector_id()));
        foundation.setSize(request.getSize());
        foundation.setVerified(request.isVerified());
        foundation.setWebsiteUrl(request.getWebsiteUrl());
        foundation.setPassword(request.getPassword());
        foundation.setTags(tagService.findByIds(request.getTagsIds()));
        return repository.save(foundation);
    }

    @Override
    public List<Foundation> findAll() {
        Pageable pageable = PageRequest.of(0, 14);
        return repository.findLimitedFoundations(pageable);
    }
}
