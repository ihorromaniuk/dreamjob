package core.basesyntax.dreamjob.repository;

import core.basesyntax.dreamjob.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
}
