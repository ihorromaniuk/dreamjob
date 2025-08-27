package core.basesyntax.dreamjob.repository;

import core.basesyntax.dreamjob.model.Job;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
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
    Page<Job> findAll(Specification<Job> specification, Pageable pageable);

    void deleteByUpdatedAtBefore(Instant createdAtBefore);
}
