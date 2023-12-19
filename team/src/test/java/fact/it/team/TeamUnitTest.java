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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamUnitTest {

    @InjectMocks
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(teamService, "driverServiceBaseUrl", "http://localhost:8079");
    }
    @Test
    public void testGetAllTeams() {
        // Arrange
        List<TeamDriver> driverList1 = new ArrayList<>();
        TeamDriver driver1 = new TeamDriver();
        driver1.setFirstName("fernando");
        driver1.setLastName("alonso");

        TeamDriver driver2 = new TeamDriver();
        driver2.setFirstName("Charles");
        driver2.setLastName("Leclerc");

        driverList1.add(driver1);
        driverList1.add(driver2);


        Team team = new Team();
        team.setName("Mclaren");
        team.setSince(new Date(1,5,2023));
        team.setTeamDriverList(driverList1);

        List<TeamDriver> driverList2 = new ArrayList<>();
        TeamDriver driver3 = new TeamDriver();
        driver3.setFirstName("Max");
        driver3.setLastName("Verstappen");

        TeamDriver driver4 = new TeamDriver();
        driver4.setFirstName("Pierre");
        driver4.setLastName("Gasly");

        driverList2.add(driver1);
        driverList2.add(driver2);


        Team team2 = new Team();
        team2.setName("RedBull");
        team2.setSince(new Date(1,5,2023));
        team2.setTeamDriverList(driverList2);

        when(teamRepository.findAll()).thenReturn(Arrays.asList(team,team2));


        // Act
        List<TeamResponse> result = teamService.getAllTeams();

        // Assert
        verify(teamRepository, times(1)).findAll();

    }
}
