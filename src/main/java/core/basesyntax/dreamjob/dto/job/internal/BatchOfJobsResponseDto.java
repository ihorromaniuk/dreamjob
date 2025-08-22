package core.basesyntax.dreamjob.dto.job.internal;

import java.util.List;

public record BatchOfJobsResponseDto(long count, List<JobsShortenedResponseDto> jobDtos) {
}
