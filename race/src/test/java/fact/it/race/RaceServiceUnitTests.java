package fact.it.race;

import fact.it.race.dto.*;
import fact.it.race.model.Race;
import fact.it.race.repository.RaceRepository;
import fact.it.race.service.RaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

// Add appropriate annotations and extensions for your testing framework, e.g., SpringBootTest or SpringExtension
public class RaceServiceUnitTests {

    @InjectMocks
    private RaceService raceService;

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private WebClient webClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        raceService = new RaceService(raceRepository, webClient);
        // Set the field using ReflectionTestUtils
        ReflectionTestUtils.setField(raceService, "raceRepository", raceRepository);

    }

    @Test
    public void testCreateRace() {
        // Create a sample RaceRequest
        RaceRequest raceRequest = new RaceRequest();
        Date sinceDate = new Date(2000, 1, 1);
        raceRequest.setRaceTeamDtoList(Collections.singletonList(new RaceTeamDto(1L,"RedBull", sinceDate)));

        // Mock the WebClient's behavior using Mockito
        TeamResponse[] teamResponses = new TeamResponse[1]; // You can set the desired response
        CircuitResponse[] circuitResponses = new CircuitResponse[1]; // You can set the desired response

        Mockito.when(webClient.get()
                .uri("http://localhost:8082/api/team")
                .retrieve()
                .bodyToMono(TeamResponse[].class)
                .block()).thenReturn(teamResponses);

        Mockito.when(webClient.get()
                .uri("http://localhost:8080/api/circuit")
                .retrieve()
                .bodyToMono(CircuitResponse[].class)
                .block()).thenReturn(circuitResponses);

        boolean result = raceService.createRace(raceRequest);

        // Add your assertions here to verify the result of createRace
        assertTrue(result);

        // You can also add more specific assertions to check the behavior based on the mocked responses.
    }

    @Test
    public void testGetAllRaces() {
        // Mock the behavior of raceRepository
        List<Race> mockRaces = new ArrayList<>();
        mockRaces.add(new Race("1", "Belgian Grand Prix", new Date(2024, 1, 1), new ArrayList<>()));
        mockRaces.add(new Race("2", "Dutch Grand Prix", new Date(2024, 2, 1), new ArrayList<>()));

        Mockito.when(raceRepository.findAll()).thenReturn(mockRaces);

        List<RaceResponse> raceResponses = raceService.getAllRaces();

        // Add assertions to verify the output of getAllRaces
        assertEquals(2, raceResponses.size());

        // Add more specific assertions based on your expected results
        assertEquals("Belgian Grand Prix", raceResponses.get(0).getName());
        assertEquals("Dutch Grand Prix", raceResponses.get(1).getName());
    }
}