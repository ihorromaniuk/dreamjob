package core.basesyntax.dreamjob.dto.job.external;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobExternalDto {
    private Long id;
    private Long createdAt;
    private String title;
    private String url;
    private Organization organization;
    private Set<String> locations;
    private boolean hasDescription;
    private String slug;
    private String seniority;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Organization {
        private Long id;
        private String logoUrl;
        private String name;
        private String slug;
        private Set<String> industryTags;
    }
}
