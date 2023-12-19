package fact.it.circuit;

import fact.it.circuit.controller.CircuitController;
import fact.it.circuit.dto.CircuitResponse;
import fact.it.circuit.model.Circuit;
import fact.it.circuit.repository.CircuitRepository;
import fact.it.circuit.service.CircuitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)

class CircuitControllerTest {
    @Mock
    private CircuitService circuitService;

    @InjectMocks
    private CircuitController circuitController;

    @Test
    void testGetCircuitById() {
        int circuitId = 1;
        CircuitResponse expectedResponse = new CircuitResponse();

        when(circuitService.getCircuitById(circuitId)).thenReturn(ResponseEntity.ok(expectedResponse));

        ResponseEntity<CircuitResponse> actualResponse = circuitController.getCircuitById(circuitId);

        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedResponse, actualResponse.getBody());
    }

    @Test
    void testGetAllCircuits() {
        List<CircuitResponse> expectedResponses = Arrays.asList(new CircuitResponse(), new CircuitResponse());

        when(circuitService.getAllCircuits()).thenReturn(expectedResponses);

        List<CircuitResponse> actualResponses = circuitController.getAllCircuits();

        assertEquals(expectedResponses.size(), actualResponses.size());
        assertTrue(expectedResponses.containsAll(actualResponses) && actualResponses.containsAll(expectedResponses));
    }
}
