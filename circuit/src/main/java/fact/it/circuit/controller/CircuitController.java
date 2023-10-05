package fact.it.circuit.controller;

import fact.it.circuit.dto.CircuitRequest;
import fact.it.circuit.dto.CircuitResponse;
import fact.it.circuit.service.CircuitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/circuit")
@RequiredArgsConstructor
public class CircuitController {
    private final CircuitService circuitService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createCircuit
            (@RequestBody CircuitRequest circuitRequest) {
        circuitService.createCircuit(circuitRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CircuitResponse> getAllCircuitsByName
            (@RequestParam List<String> name) {
        return circuitService.getAllCircuitsByName(name);
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<CircuitResponse> getAllCircuits() {
        return circuitService.getAllCircuits();
    }
}
