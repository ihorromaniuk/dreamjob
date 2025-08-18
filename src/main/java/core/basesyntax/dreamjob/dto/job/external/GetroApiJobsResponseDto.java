package core.basesyntax.dreamjob.dto.job.external;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetroApiJobsResponseDto {
    private Results results;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Results {
        private int count;
        private List<JobExternalDto> jobs;
    }
}
