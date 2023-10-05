package fact.it.circuit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CircuitRequest {
    private String name;
    private Integer lenght;
    private String country;
}
