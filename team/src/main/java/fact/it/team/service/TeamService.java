package fact.it.team.service;

import fact.it.team.dto.TeamResponse;
import fact.it.team.model.TeamMember;
import fact.it.team.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @PostConstruct
    public void loadData() {
        if(teamRepository.count() > 0){
            TeamMember teamMember = new TeamMember();
            teamMember.setFirstName("Michiel");
            teamMember.setLastName("Van Loy");

            TeamMember teamMember1 = new TeamMember();
            teamMember1.setFirstName("Bent");
            teamMember1.setLastName("Melis");

            teamRepository.save(teamMember);
            teamRepository.save(teamMember1);
        }
    }

    @Transactional(readOnly = true)
    public List<TeamResponse> isInTeam(List<String> firstName) {

        return teamRepository.findByFirstName(firstName).stream()
                .map(teamMember ->
                        TeamResponse.builder()
                                .firstName(teamMember.getFirstName())
                                .lastName(teamMember.getLastName())
                                .build()
                ).toList();
    }
}