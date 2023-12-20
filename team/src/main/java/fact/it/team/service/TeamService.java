package fact.it.team.service;

import fact.it.team.dto.TeamResponse;
import fact.it.team.model.Team;
import fact.it.team.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    @PostConstruct
    public void loadData() {
        Team team = new Team();
        team.setName("RedBull");
        team.setSince(new Date(2020, Calendar.JANUARY,12));

        Team team1 = new Team();
        team1.setName("Mercedes");
        team1.setSince(new Date(2019, Calendar.JANUARY,16));

        Team team2 = new Team();
        team2.setName("Ferrari");
        team2.setSince(new Date(2012, Calendar.JANUARY,25));

        teamRepository.save(team);
        teamRepository.save(team1);
        teamRepository.save(team2);
    }
    public List<TeamResponse> getAllTeams() {
        List<Team> products = teamRepository.findAll();

        return products.stream().map(this::mapToTeamResponse).toList();
    }
    public ResponseEntity<TeamResponse> getTeamById(int id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if(optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            return ResponseEntity.ok(mapToTeamResponse(team));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private TeamResponse mapToTeamResponse(Team team) {
        return TeamResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .since(team.getSince())
                .build();
    }
}