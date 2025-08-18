package core.basesyntax.dreamjob.repository;

import core.basesyntax.dreamjob.model.Job;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
    @EntityGraph(attributePaths = {
            "organization",
            "organization.industryTags",
            "laborFunctions",
            "locations"
    })
    Optional<Job> findById(Long id);

    @EntityGraph(attributePaths = {
            "organization",
            "laborFunctions",
            "locations"
    })
    Page<Job> findAllByLaborFunctionsContaining(Set<String> laborFunctions, Pageable pageable);
}
