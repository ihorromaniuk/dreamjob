package core.basesyntax.dreamjob.dto.job.external;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDetailsDto {
    private String techStarsUrl;
    private Set<String> laborFunctions;
    private String description;
    private Set<String> locations;
}
