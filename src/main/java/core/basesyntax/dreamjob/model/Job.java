package core.basesyntax.dreamjob.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {
    @Id
    private Long id;
    private String jobPageUrl;
    private String positionName;
    private String organizationJobPageUrl;
    @ManyToOne
    private Organization organization;
    @ElementCollection
    @EqualsAndHashCode.Exclude
    private List<String> laborFunctions;
    @ElementCollection
    @EqualsAndHashCode.Exclude
    private List<String> locations;
    private Long postedDate;
    private String description;
    @ElementCollection
    @EqualsAndHashCode.Exclude
    private List<String> tags;
}
