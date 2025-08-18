package core.basesyntax.dreamjob.dto.job.internal;

import java.util.List;

public record BatchOfJobsResponseDto(int count, List<JobsShortenedResponseDto> jobDtos) {
}
