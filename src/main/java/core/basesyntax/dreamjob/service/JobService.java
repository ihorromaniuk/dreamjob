package core.basesyntax.dreamjob.service;

import core.basesyntax.dreamjob.dto.job.internal.BatchOfJobsResponseDto;
import core.basesyntax.dreamjob.dto.job.internal.JobRequestDto;
import core.basesyntax.dreamjob.dto.job.internal.JobResponseDto;
import core.basesyntax.dreamjob.dto.job.internal.JobsShortenedResponseDto;
import core.basesyntax.dreamjob.exception.EntityNotFoundException;
import core.basesyntax.dreamjob.mapper.JobMapper;
import core.basesyntax.dreamjob.model.Job;
import core.basesyntax.dreamjob.repository.JobRepository;
import core.basesyntax.dreamjob.repository.job.JobSpecificationBuilder;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final JobSpecificationBuilder jobSpecificationBuilder;

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

    public BatchOfJobsResponseDto getJobs(JobRequestDto requestDto) {
        Pageable pageable = Pageable
                .ofSize(requestDto.getHitsPerPage())
                .withPage(requestDto.getPage());
        Specification<Job> specification = jobSpecificationBuilder.build(requestDto.getFilters());
        Page<Job> page = jobRepository.findAll(specification, pageable);
        List<JobsShortenedResponseDto> jobs = page
                .stream()
                .map(jobMapper::toShortenedDto)
                .toList();
        return new BatchOfJobsResponseDto(page.getTotalElements(), jobs);
    }

    public void deleteJobsByUpdatedAtBefore(Instant instant) {
        jobRepository.deleteByUpdatedAtBefore(instant);
    }
}
