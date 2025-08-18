package core.basesyntax.dreamjob.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job {
    @Id
    private Long id;

    private String jobPageUrl;

    private String positionName;

    private String organizationJobPageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private Organization organization;

    @ElementCollection
    @EqualsAndHashCode.Exclude
    private Set<String> laborFunctions;

    @ElementCollection
    @EqualsAndHashCode.Exclude
    private Set<String> locations;

    private Long postedDate;

    private String description;

    private String seniority;
}
