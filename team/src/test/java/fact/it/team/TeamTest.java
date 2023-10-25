package fact.it.team.service;

import fact.it.team.dto.*;
import fact.it.team.model.Team;
import fact.it.team.model.TeamDriver;
import fact.it.team.repository.TeamRepository;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.reflect.Array.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamTest {

    @InjectMocks
    private TeamService teamService;

    @InjectMocks
    private TeamRequest teamRequest;

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(teamService, "driverServiceBaseUrl", "http://localhost:8079");
    }

    @Test
    public void testIsInTeam() {
        // Arrange
        List<String> names = List.of("Mclaren");

        when(teamRepository.findByFirstName(names)).thenReturn(List.of(
                new Team("Mclaren", new Date(2020, Calendar.JANUARY, 12)),
                new Team("Mclaren", new Date(2019, Calendar.JANUARY, 16))
        ));

        // Act
        List<TeamResponse> result = teamService.isInTeam(names);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Mclaren", result.get(0).getName());
        assertEquals(new Date(2020, Calendar.JANUARY, 12), result.get(0).getSince());
        assertEquals("Mclaren", result.get(1).getName());
        assertEquals(new Date(2019, Calendar.JANUARY, 16), result.get(1).getSince());
    }


    @Test
    public void testCreateTeam() {
        // Arrange
        TeamRequest teamRequest = new TeamRequest();
        TeamDriverDto driverDto = new TeamDriverDto("John", "Doe");
        teamRequest.setTeamDriverDtoList(List.of(driverDto));

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(), Optional.ofNullable(any()))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(DriverResponse[].class)).thenReturn(Mono.just(new DriverResponse[0]));

        // Act
        boolean result = teamService.createTeam(teamRequest);

        // Assert
        assertTrue(result);
        // Add more assertions as needed for your specific test case
    }

    @Test
    public void testGetAllTeams() {
        // Arrange
        when(teamRepository.findAll()).thenReturn(List.of(
                new Team("Mclaren", new Date(2020, Calendar.JANUARY, 12)),
                new Team("Ferrari", new Date(2021, Calendar.FEBRUARY, 15))
        ));

        // Act
        List<TeamResponse> result = teamService.getAllTeams();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Mclaren", result.get(0).getName());
        assertEquals(new Date(2020, Calendar.JANUARY, 12), result.get(0).getSince());
        assertEquals("Ferrari", result.get(1).getName());
        assertEquals(new Date(2021, Calendar.FEBRUARY, 15), result.get(1).getSince());
        // Add more assertions as needed for your specific test case
    }
}
