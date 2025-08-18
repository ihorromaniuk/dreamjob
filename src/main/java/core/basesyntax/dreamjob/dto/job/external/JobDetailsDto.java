package core.basesyntax.dreamjob.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDetailsDto {
    private List<String> laborFunctions;
    private String description;
    private List<String> locations;
}
