package fact.it.team.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamDriverDto {
    private Long id;
    private String firstName;
    private String lastName;

    public TeamDriverDto(String john, String doe) {
    }
}
