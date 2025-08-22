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
public class LaborFunctionSpecification {
    public Specification<Job> getSpecification(Set<String> laborFunctions) {
        return new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                return laborFunctions.stream()
                        .map(s -> criteriaBuilder.isMember(s, root.get("laborFunctions")))
                        .reduce(criteriaBuilder::or)
                        .orElseThrow();
            }
        };
    }
}
