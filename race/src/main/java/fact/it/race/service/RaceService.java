package fact.it.race.service;

import fact.it.race.dto.*;
import fact.it.race.model.Race;
import fact.it.race.model.RaceTeam;
import fact.it.race.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class RaceService {

    private final RaceRepository raceRepository;
    private final WebClient webClient;

    @Value("${circuitservice.baseurl}")
    private String circuitServiceBaseUrl;

    @Value("${teamservice.baseurl}")
    private String teamServiceBaseUrl;

    public boolean createRace(RaceRequest raceRequest) {
        Race race = new Race();
        race.setId(UUID.randomUUID().toString());

        List<RaceTeam> raceTeams = raceRequest.getRaceTeamDtoList()
                .stream()
                .map(this::mapToRaceTeam)
                .toList();

        race.setRaceTeamList(raceTeams);

        List<String> names = race.getRaceTeamList().stream()
                .map(RaceTeam::getName)
                .toList();

        TeamResponse[] teamResponseArray = webClient.get()
                .uri("http://"+teamServiceBaseUrl+"/api/team",
                        uriBuilder -> uriBuilder.queryParam("name", names).build())
                .retrieve()
                .bodyToMono(TeamResponse[].class)
                .block();

        CircuitResponse[] circuitResponseArray = webClient.get()
                .uri("http://"+ circuitServiceBaseUrl+"/api/circuit",
                        uriBuilder -> uriBuilder.queryParam("name", names).build())
                .retrieve()
                .bodyToMono(CircuitResponse[].class)
                .block();

        raceRepository.save(race);
        return true;
    }

    public List<RaceResponse> getAllRaces() {
        List<Race> races = raceRepository.findAll();

        return races.stream()
                .map(race -> new RaceResponse(
                        race.getRaceName(),
                        mapToRaceTeamDto(race.getRaceTeamList())
                ))
                .collect(Collectors.toList());
    }

    private RaceTeam mapToRaceTeam(RaceTeamDto raceTeamDto) {
        RaceTeam raceTeam = new RaceTeam();
        raceTeam.setName(raceTeamDto.getName());
        raceTeam.setSince(raceTeamDto.getSince());
        return raceTeam;
    }

    private List<RaceTeamDto> mapToRaceTeamDto(List<RaceTeam> raceTeams) {
        return raceTeams.stream()
                .map(raceTeam -> new RaceTeamDto(
                        raceTeam.getId(),
                        raceTeam.getName(),
                        raceTeam.getSince()
                ))
                .collect(Collectors.toList());
    }
}