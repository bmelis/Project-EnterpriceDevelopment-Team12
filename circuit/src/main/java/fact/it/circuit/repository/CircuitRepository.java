package fact.it.circuit.repository;

import fact.it.circuit.model.Circuit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CircuitRepository extends MongoRepository<Circuit, Integer> {

}
