package core.basesyntax.dreamjob.service;

import core.basesyntax.dreamjob.dto.job.internal.BatchOfJobsResponseDto;
import core.basesyntax.dreamjob.dto.job.internal.JobRequestDto;
import core.basesyntax.dreamjob.dto.job.internal.JobResponseDto;
import core.basesyntax.dreamjob.dto.job.internal.JobsShortenedResponseDto;
import core.basesyntax.dreamjob.exception.EntityNotFoundException;
import core.basesyntax.dreamjob.mapper.JobMapper;
import core.basesyntax.dreamjob.model.Job;
import core.basesyntax.dreamjob.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;

    public Job save(Job job) {
        return jobRepository.save(job);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find job by id. Id: " + id
                ));
    }

    public JobResponseDto getJobResponseById(Long id) {
        return jobMapper.toDto(getJobById(id));
    }

    public BatchOfJobsResponseDto getJobs(JobRequestDto requestBody) {
        Pageable pageable = Pageable
                .ofSize(requestBody.getHitsPerPage())
                .withPage(requestBody.getPage());
        Page<JobsShortenedResponseDto> pageOfJobs = jobRepository
                .findAllByLaborFunctionsContaining(requestBody.getFilters().getJobFunctions(),
                        pageable)
                .map(jobMapper::toShortenedDto);

        return new BatchOfJobsResponseDto((int) pageOfJobs.getTotalElements(),
                pageOfJobs.toList());
    }
}
