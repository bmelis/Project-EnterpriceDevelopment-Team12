package fact.it.race.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceTeamDto {
    private Long id;
    private String name;
    private Date since;
}