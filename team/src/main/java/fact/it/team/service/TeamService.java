package fact.it.team.service;

import fact.it.team.dto.DriverResponse;
import fact.it.team.dto.TeamDriverDto;
import fact.it.team.dto.TeamRequest;
import fact.it.team.dto.TeamResponse;
import fact.it.team.model.Team;
import fact.it.team.model.TeamDriver;
import fact.it.team.repository.TeamRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final WebClient webClient;

    @Value("${driverservice.baseurl}")
    private String driverServiceBaseUrl;

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
    public boolean createTeam(TeamRequest teamRequest) {
        Team team = new Team();

        List<TeamDriver> teamDrivers = teamRequest.getTeamDriverDtoList()
                .stream()
                .map(this::mapToTeamDriver)
                .toList();

        team.setTeamDriverList(teamDrivers);

        List<String> names = team.getTeamDriverList().stream()
                .map(TeamDriver::getFirstName)
                .toList();

        DriverResponse[] teamResponseArray = webClient.get()
                .uri("http://"+ driverServiceBaseUrl+ "api/driver",
                        uriBuilder -> uriBuilder.queryParam("firstname", names).build())
                .retrieve()
                .bodyToMono(DriverResponse[].class)
                .block();

        return true;
    }

    public List<TeamResponse> getAllTeams() {
        List<Team> teams = teamRepository.findAll();

        return teams.stream()
                .map(team -> new TeamResponse(
                        team.getName(),
                        team.getSince(),
                        mapToTeamDriverDto(team.getTeamDriverList())
                ))
                .collect(Collectors.toList());
    }

    private TeamDriver mapToTeamDriver(TeamDriverDto teamDriverDto) {
        TeamDriver teamDriver = new TeamDriver();
        teamDriver.setFirstName(teamDriverDto.getFirstName());
        teamDriver.setLastName(teamDriverDto.getLastName());
        return teamDriver;
    }

    private List<TeamDriverDto> mapToTeamDriverDto(List<TeamDriver> teamDrivers) {
        return teamDrivers.stream()
                .map(teamDriver -> new TeamDriverDto(
                        teamDriver.getId(),
                        teamDriver.getFirstName(),
                        teamDriver.getLastName()
                ))
                .collect(Collectors.toList());
    }


}