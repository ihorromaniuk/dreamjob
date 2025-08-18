package core.basesyntax.dreamjob.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Organization {
    @Id
    private Long id;
    private String title;
    private String logoUrl;
}
