package fact.it.team.repository;

import fact.it.team.model.Team;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByFirstName(List<String> firstName);
}