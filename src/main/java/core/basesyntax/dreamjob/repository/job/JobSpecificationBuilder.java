package core.basesyntax.dreamjob.repository.job;

import core.basesyntax.dreamjob.dto.job.internal.JobRequestDto;
import core.basesyntax.dreamjob.model.Job;
import core.basesyntax.dreamjob.repository.SpecificationBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JobSpecificationBuilder implements
        SpecificationBuilder<Job, JobRequestDto.Filters> {
    private final LaborFunctionSpecification laborFunctionSpecification;
    private final LocationSpecification locationSpecification;
    private final SenioritySpecification senioritySpecification;

    @Override
    public Specification<Job> build(JobRequestDto.Filters params) {
        Specification<Job> spec = Specification.unrestricted();
        if (params.getJobFunctions() != null && !params.getJobFunctions().isEmpty()) {
            spec = spec.and(laborFunctionSpecification
                    .getSpecification(params.getJobFunctions()));
        }
        if (params.getLocations() != null && !params.getLocations().isEmpty()) {
            spec = spec.and(locationSpecification
                    .getSpecification(params.getLocations()));
        }
        if (params.getSeniorities() != null && !params.getSeniorities().isEmpty()) {
            spec = spec.and(senioritySpecification
                    .getSpecification(params.getSeniorities()));
        }
        return spec;
    }
}
