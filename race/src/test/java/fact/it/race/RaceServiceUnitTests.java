package fact.it.race;

import fact.it.race.controller.RaceController;
import fact.it.race.dto.*;
import fact.it.race.service.RaceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RaceServiceUnitTests {
    @InjectMocks
    private RaceController raceController;

    @Mock
    private RaceService raceService;

    @Test
    public void testCreateRace() {
        // Arrange
        RaceRequest raceRequest = new RaceRequest();
        when(raceService.createRace(any())).thenReturn(new RaceResponse());

        // Act
        RaceResponse raceResponse = raceController.createRace(raceRequest);

        // Assert
        assertNotNull(raceResponse);
        verify(raceService, times(1)).createRace(eq(raceRequest));
    }

    @Test
    public void testUpdateRace() {
        // Arrange
        int raceId = 1;
        RaceRequest raceRequest = new RaceRequest();
        when(raceService.updateRace(any(), eq(raceId))).thenReturn(new RaceResponse());

        // Act
        RaceResponse raceResponse = raceController.updateRace(raceId, raceRequest);

        // Assert
        assertNotNull(raceResponse);
        verify(raceService, times(1)).updateRace(eq(raceRequest), eq(raceId));
    }

    @Test
    public void testGetAllRaces() {
        // Arrange
        List<RaceResponse> raceResponses = Arrays.asList(new RaceResponse(), new RaceResponse());
        when(raceService.getAllRaces()).thenReturn(raceResponses);

        // Act
        List<RaceResponse> result = raceController.getAllRaces();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(raceService, times(1)).getAllRaces();
    }

    @Test
    public void testGetRaceById() {
        // Arrange
        int raceId = 1;
        when(raceService.findById(eq(raceId))).thenReturn(new RaceResponse());

        // Act
        RaceResponse raceResponse = raceController.getRaceById(raceId);

        // Assert
        assertNotNull(raceResponse);
        verify(raceService, times(1)).findById(eq(raceId));
    }

    @Test
    public void testDeleteRace() {
        // Arrange
        int raceId = 1;
        doNothing().when(raceService).deleteRace(eq(raceId));

        // Act
        raceController.deleteRace(raceId);

        // Assert
        verify(raceService, times(1)).deleteRace(eq(raceId));
    }
}