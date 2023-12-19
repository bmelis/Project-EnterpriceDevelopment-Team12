package fact.it.race.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RaceRequest {
    private String raceName;
    private Date raceDate;
    private int circuitId;
    private int teamId;
}