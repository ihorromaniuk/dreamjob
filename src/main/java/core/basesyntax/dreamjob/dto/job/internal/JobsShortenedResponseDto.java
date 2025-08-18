package core.basesyntax.dreamjob.dto.job.internal;

import java.util.Set;

public record JobsShortenedResponseDto(Long id,
                                       String positionName,
                                       String organizationJobPageUrl,
                                       String logoUrl,
                                       String organizationTitle,
                                       Set<String> laborFunctions,
                                       Set<String> locations,
                                       Long postedDate) {
}
