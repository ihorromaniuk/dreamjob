package core.basesyntax.dreamjob.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Set;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "organization")
public class Organization {
    @Id
    private Long id;

    private String title;

    private String logoUrl;

    @ElementCollection
    @EqualsAndHashCode.Exclude
    private Set<String> industryTags;
}
