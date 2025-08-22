package core.basesyntax.dreamjob.repository;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T, P> {
    Specification<T> build(P params);
}
