package core.basesyntax.dreamjob.dto.job.internal;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequestDto {
    @Schema(name = "hits_per_page")
    private int hitsPerPage;
    private int page;
    private JobRequestDto.Filters filters;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Filters {
        @Schema(name = "job_functions")
        private Set<String> jobFunctions;
    }
}
