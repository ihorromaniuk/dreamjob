package core.basesyntax.dreamjob.service;

import core.basesyntax.dreamjob.mapper.OrganizationMapper;
import core.basesyntax.dreamjob.model.Organization;
import core.basesyntax.dreamjob.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }
}
