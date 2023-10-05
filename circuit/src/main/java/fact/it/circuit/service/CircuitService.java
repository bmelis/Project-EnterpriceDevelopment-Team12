package fact.it.circuit.service;

import fact.it.circuit.dto.CircuitRequest;
import fact.it.circuit.dto.CircuitResponse;
import fact.it.circuit.model.Circuit;
import fact.it.circuit.repository.CircuitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CircuitService {
    private final CircuitRepository circuitRepository;

    public void createCircuit(CircuitRequest circuitRequest){
        Circuit circuit = Circuit.builder()
                .name(circuitRequest.getName())
                .lenght(circuitRequest.getLenght())
                .country(circuitRequest.getCountry())
                .build();

        circuitRepository.save(circuit);
    }
    public List<CircuitResponse> getAllCircuits() {
        List<Circuit> products = circuitRepository.findAll();

        return products.stream().map(this::mapToCircuitResponse).toList();
    }
    public List<CircuitResponse> getAllCircuitsByName(List<String> name) {
        List<Circuit> products = circuitRepository.findByName(name);

        return products.stream().map(this::mapToCircuitResponse).toList();
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
