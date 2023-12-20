package fact.it.team;

import fact.it.team.dto.*;
import fact.it.team.model.Team;
import fact.it.team.repository.TeamRepository;
import fact.it.team.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamUnitTest {

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Test
    public void testGetAllTeams() {
        Team team = new Team();
        team.setName("Mercedes");
        team.setSince(new Date(1,5,2023));


        Team team2 = new Team();
        team2.setName("RedBull");
        team2.setSince(new Date(1,5,2023));

        when(teamRepository.findAll()).thenReturn(Arrays.asList(team,team2));

        // Act
        List<TeamResponse> result = teamService.getAllTeams();

        // Assert
        verify(teamRepository, times(1)).findAll();

    }
    @Test
    public void testGetTeamsById() {
        Team team = new Team();
        team.setName("Mercedes");
        team.setSince(new Date(1,5,2023));

        when(teamRepository.findById(1)).thenReturn(Optional.of(team));

        // Act
        ResponseEntity<TeamResponse> responseEntity = teamService.getTeamById(1);
        TeamResponse result = responseEntity.getBody();

        // Assert
        assertEquals("Mercedes", result.getName());
    }
}
