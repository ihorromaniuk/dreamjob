package core.basesyntax.dreamjob.dto.job.external;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class GetroApiJobsRequestBody {
    private int hitsPerPage;
    private int page;
    private Filters filters;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Filters {
        private Set<String> jobFunctions = new HashSet<>();
    }
}
