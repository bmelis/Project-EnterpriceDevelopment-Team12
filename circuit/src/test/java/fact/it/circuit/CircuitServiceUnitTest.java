package fact.it.circuit;

import fact.it.circuit.dto.CircuitRequest;
import fact.it.circuit.dto.CircuitResponse;
import fact.it.circuit.model.Circuit;
import fact.it.circuit.repository.CircuitRepository;
import fact.it.circuit.service.CircuitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CircuitServiceUnitTest {

    private CircuitService circuitService;

    @Mock
    private CircuitRepository circuitRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks
        circuitService = new CircuitService(circuitRepository);
    }

    @Test
    void createCircuitTest() {
        // Given
        CircuitRequest circuitRequest = new CircuitRequest();
        circuitRequest.setName("Spa");
        circuitRequest.setLenght(1000);
        circuitRequest.setCountry("Belgium");

        // When
        circuitService.createCircuit(circuitRequest);

        // Then
        Mockito.verify(circuitRepository, Mockito.times(1)).save(Mockito.any(Circuit.class));
    }

    @Test
    void getAllCircuitsTest() {
        // Arrange
        Circuit circuit = new Circuit();
        circuit.setName("Spa");
        circuit.setLenght(1000);
        circuit.setCountry("Belgium");

        when(circuitRepository.findAll()).thenReturn(Arrays.asList(circuit));

        // Act
        List<CircuitResponse> circuits = circuitService.getAllCircuits();

        // Assert
        assertEquals(1, circuits.size());
        assertEquals("Spa", circuits.get(0).getName());
        assertEquals(1000, circuits.get(0).getLenght());
        assertEquals("Belgium", circuits.get(0).getCountry());

        verify(circuitRepository, times(1)).findAll();
    }

    @Test
    void getAllCircuitsByNameTest() {
        // Arrange
        Circuit circuit = new Circuit();
        circuit.setName("Spa");
        circuit.setLenght(1000);
        circuit.setCountry("Belgium");

        when(circuitRepository.findByName(Arrays.asList("Spa"))).thenReturn(Arrays.asList(circuit));

        // Act
        List<CircuitResponse> circuits = circuitService.getAllCircuitsByName(Arrays.asList("Spa"));

        // Assert
        assertEquals(1, circuits.size());
        assertEquals("Spa", circuits.get(0).getName());
        assertEquals(1000, circuits.get(0).getLenght());
        assertEquals("Belgium", circuits.get(0).getCountry());

        verify(circuitRepository, times(1)).findByName(Arrays.asList(circuit.getName()));
    }
}