package fact.it.team.repository;

import fact.it.team.model.TeamMember;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
@Transactional
public interface TeamRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findByFirstName(List<String> firstName);
}