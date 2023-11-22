package fact.it.driver.repository;

import fact.it.driver.model.Driver;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface DriverRepository extends JpaRepository<Driver, Long> {
    List<Driver> findByFirstName(String firstName);

}
