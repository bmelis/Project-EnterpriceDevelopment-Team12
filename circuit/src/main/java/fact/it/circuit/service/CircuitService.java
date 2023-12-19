package fact.it.circuit.service;

import fact.it.circuit.dto.CircuitRequest;
import fact.it.circuit.dto.CircuitResponse;
import fact.it.circuit.model.Circuit;
import fact.it.circuit.repository.CircuitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CircuitService {
    private final CircuitRepository circuitRepository;
    public List<CircuitResponse> getAllCircuits() {
        List<Circuit> products = circuitRepository.findAll();

        return products.stream().map(this::mapToCircuitResponse).toList();
    }
    public ResponseEntity<CircuitResponse> getCircuitById(int id) {
        Optional<Circuit> optionalCircuit = circuitRepository.findById(id);
        if(optionalCircuit.isPresent()) {
            Circuit circuit = optionalCircuit.get();
            return ResponseEntity.ok(mapToCircuitResponse(circuit));
        } else {
            // Return a ResponseEntity with a 404 Not Found status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    private CircuitResponse mapToCircuitResponse(Circuit circuit) {
        return CircuitResponse.builder()
                .id(circuit.getId())
                .name(circuit.getName())
                .lenght(circuit.getLenght())
                .country(circuit.getCountry())
                .build();
    }
}
