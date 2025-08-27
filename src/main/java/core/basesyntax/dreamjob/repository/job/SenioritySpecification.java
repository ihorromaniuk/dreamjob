package core.basesyntax.dreamjob.repository.job;

import core.basesyntax.dreamjob.model.Job;
import java.util.Set;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class SenioritySpecification {
    public Specification<Job> getSpecification(Set<String> seniorities) {
        return (root, query, criteriaBuilder) ->
                root.get("seniority").in(seniorities);
    }
}
