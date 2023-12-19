package fact.it.circuit.controller;

import fact.it.circuit.dto.CircuitRequest;
import fact.it.circuit.dto.CircuitResponse;
import fact.it.circuit.service.CircuitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/circuit")
@RequiredArgsConstructor
public class CircuitController {
    private final CircuitService circuitService;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CircuitResponse> getCircuitById(@PathVariable int id) {
        return circuitService.getCircuitById(id);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CircuitResponse> getAllCircuits() {
        return circuitService.getAllCircuits();
    }
}
