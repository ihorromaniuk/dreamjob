package core.basesyntax.dreamjob.repository.job;

import core.basesyntax.dreamjob.model.Job;
import java.util.Set;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class LocationSpecification {
    public Specification<Job> getSpecification(Set<String> locations) {
        return (root, query, criteriaBuilder) ->
                root.join("locations").in(locations);
    }
}
