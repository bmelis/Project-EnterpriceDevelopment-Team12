package fact.it.circuit.service;

import fact.it.circuit.dto.CircuitRequest;
import fact.it.circuit.dto.CircuitResponse;
import fact.it.circuit.model.Circuit;
import fact.it.circuit.repository.CircuitRepository;
import jakarta.annotation.PostConstruct;
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
    @PostConstruct
    public void loadData() {
        if(circuitRepository.count() > 0){
            Circuit circuit = new Circuit();
            circuit.setId(1);
            circuit.setCountry("Belgium");
            circuit.setLenght(7004);

            Circuit circuit1 = new Circuit();
            circuit1.setId(2);
            circuit1.setCountry("Netherlands");
            circuit1.setLenght(4259);

            Circuit circuit2 = new Circuit();
            circuit2.setId(3);
            circuit2.setCountry("Spain");
            circuit2.setLenght(4675);

            Circuit circuit3 = new Circuit();
            circuit3.setId(4);
            circuit3.setCountry("Japan");
            circuit3.setLenght(5807);

            Circuit circuit4 = new Circuit();
            circuit4.setId(5);
            circuit4.setCountry("Mexico");
            circuit4.setLenght(4304);

            circuitRepository.save(circuit);
            circuitRepository.save(circuit1);
            circuitRepository.save(circuit2);
            circuitRepository.save(circuit3);
            circuitRepository.save(circuit4);
        }
    }
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
