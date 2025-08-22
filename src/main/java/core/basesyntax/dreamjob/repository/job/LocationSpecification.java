package core.basesyntax.dreamjob.repository.job;

import core.basesyntax.dreamjob.model.Job;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.Set;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class LocationSpecification {
    public Specification<Job> getSpecification(Set<String> locations) {
        return new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return locations.stream()
                        .map(s -> criteriaBuilder.isMember(s, root.get("locations")))
                        .reduce(criteriaBuilder::or)
                        .orElseThrow();
            }
        };
    }
}
