package fact.it.circuit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(value = "circuit")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Circuit {
    private int id;
    private String name;
    private Integer lenght;
    private String country;
}
