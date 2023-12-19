package fact.it.race.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RaceResponse {
    private int id;
    private String raceName;
    private Date raceDate;
    private int circuitId;
    private int teamId;
}