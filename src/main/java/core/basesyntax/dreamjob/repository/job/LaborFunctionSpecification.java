package core.basesyntax.dreamjob.repository.job;

import core.basesyntax.dreamjob.model.Job;
import java.util.Set;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class LaborFunctionSpecification {
    public Specification<Job> getSpecification(Set<String> laborFunctions) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            return root.join("laborFunctions").in(laborFunctions);
        };
    }
}
