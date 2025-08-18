package core.basesyntax.dreamjob.dto.job.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private Long id;
    private Long createdAt;
    private String title;
    private String url;
    private Organization organization;
    private List<String> locations;
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
        private List<String> industryTags;
    }
}
