package fact.it.circuit.repository;

import fact.it.circuit.model.Circuit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CircuitRepository extends MongoRepository<Circuit, String> {
    List<Circuit> findByName(List<String> name);

}
