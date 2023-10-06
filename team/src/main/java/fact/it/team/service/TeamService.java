package fact.it.team.service;

import fact.it.team.dto.TeamResponse;
import fact.it.team.model.Team;
import fact.it.team.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @PostConstruct
    public void loadData() {
        if(teamRepository.count() > 0){
            Team team = new Team();
            team.setName("Mclaren");
            team.setSince(new Date(2020, Calendar.JANUARY,12));

            Team team1 = new Team();
            team1.setName("Mclaren");
            team1.setSince(new Date(2019, Calendar.JANUARY,16));

            teamRepository.save(team);
            teamRepository.save(team1);
        }
    }



    @Transactional(readOnly = true)
    public List<TeamResponse> isInTeam(List<String> name) {

        return teamRepository.findByFirstName(name).stream()
                .map(team ->
                        TeamResponse.builder()
                                .name(team.getName())
                                .since(team.getSince())
                                .build()
                ).toList();
    }
}