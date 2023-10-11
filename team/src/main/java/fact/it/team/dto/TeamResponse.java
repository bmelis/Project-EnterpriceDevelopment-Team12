package fact.it.team.dto;

import fact.it.team.model.TeamDriver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamResponse {
    private String name;
    private Date since;
    private List<TeamDriverDto> teamDriverList;
}
