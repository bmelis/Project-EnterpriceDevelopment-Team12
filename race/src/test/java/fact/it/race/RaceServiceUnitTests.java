package fact.it.race;

import fact.it.race.dto.*;
import fact.it.race.model.Race;
import fact.it.race.model.RaceTeam;
import fact.it.race.repository.RaceRepository;
import fact.it.race.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

// Add appropriate annotations and extensions for your testing framework, e.g., SpringBootTest or SpringExtension
@ExtendWith(MockitoExtension.class)
public class RaceServiceUnitTests {

    @InjectMocks
    private RaceService raceService;

    @Mock
    private RaceRepository raceRepository;

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
        MockitoAnnotations.initMocks(this);
        raceService = new RaceService(raceRepository, webClient);
        // Set the field using ReflectionTestUtils
        ReflectionTestUtils.setField(raceService, "raceRepository", raceRepository);
        ReflectionTestUtils.setField(raceService, "circuitServiceBaseUrl", "http://localhost:8080");
        ReflectionTestUtils.setField(raceService, "teamServiceBaseUrl", "http://localhost:8082");

    }

    @Test
    public void testCreateRace() {
        Date sinceDate = new Date(2000, 1, 1);
        RaceRequest raceRequest = new RaceRequest();
        // populate raceRequest with test data
        RaceTeamDto raceTeamDto = new RaceTeamDto();
        raceTeamDto.setId(1L);
        raceTeamDto.setName("Redbull");
        raceTeamDto.setSince(sinceDate);
        raceRequest.setRaceTeamDtoList(Arrays.asList(raceTeamDto));

        TeamResponse teamResponse = new TeamResponse();
        // populate teamResponse with test data
        teamResponse.setName("Redbull");
        teamResponse.setSince(sinceDate);

        CircuitResponse circuitResponse = new CircuitResponse();
        // populate circuitResponse with test data
        circuitResponse.setId("1");
        circuitResponse.setCountry("Belgium");
        circuitResponse.setName("Spa");
        circuitResponse.setLength(1000);

        Race race = new Race();
        race.setId("1");
        race.setRaceDate(sinceDate);
        race.setRaceName("Spa");
        RaceTeam raceTeam = new RaceTeam();
        raceTeam.setId(1L);
        raceTeam.setName("Redbull");
        raceTeam.setSince(sinceDate);
        race.setRaceTeamList(Arrays.asList(raceTeam));

        when(raceRepository.save(any(Race.class))).thenReturn(race);

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(anyString(),  any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(TeamResponse[].class)).thenReturn(Mono.just(new TeamResponse[]{teamResponse}));
        when(responseSpec.bodyToMono(CircuitResponse[].class)).thenReturn(Mono.just(new CircuitResponse[]{circuitResponse}));

        // Act
        boolean result = raceService.createRace(raceRequest);

        // Assert
        assertTrue(result);

        verify(raceRepository, times(1)).save(any(Race.class));
    }

    @Test
    public void testGetAllRaces() {
        // Mock the behavior of raceRepository
        List<Race> mockRaces = new ArrayList<>();
        mockRaces.add(new Race("1", "Belgian Grand Prix", new Date(2024, 1, 1), new ArrayList<>()));
        mockRaces.add(new Race("2", "Dutch Grand Prix", new Date(2024, 2, 1), new ArrayList<>()));

        when(raceRepository.findAll()).thenReturn(mockRaces);

        List<RaceResponse> raceResponses = raceService.getAllRaces();

        // Add assertions to verify the output of getAllRaces
        assertEquals(2, raceResponses.size());

        // Add more specific assertions based on your expected results
        assertEquals("Belgian Grand Prix", raceResponses.get(0).getName());
        assertEquals("Dutch Grand Prix", raceResponses.get(1).getName());
    }
}