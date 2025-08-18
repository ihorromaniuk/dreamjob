package core.basesyntax.dreamjob.dto.job.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobsResponseDto {
    private Results results;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Results {
        private int count;
        private List<JobDto> jobs;
    }
}
