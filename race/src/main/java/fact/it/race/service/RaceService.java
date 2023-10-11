package fact.it.race.service;

import fact.it.race.dto.*;
import fact.it.race.model.Race;
import fact.it.race.model.RaceTeam;
import fact.it.race.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
                .uri("http://localhost:8082/api/team",
                        uriBuilder -> uriBuilder.queryParam("name", names).build())
                .retrieve()
                .bodyToMono(TeamResponse[].class)
                .block();

        CircuitResponse[] circuitResponseArray = webClient.get()
                .uri("http://localhost:8080/api/circuit",
                        uriBuilder -> uriBuilder.queryParam("name", names).build())
                .retrieve()
                .bodyToMono(CircuitResponse[].class)
                .block();

//        race.getRaceTeamList().stream()
//                .map(raceTeam -> {
//                    CircuitResponse circuit = Arrays.stream(productResponseArray)
//                            .filter(p -> p.getSkuCode().equals(orderItem.getSkuCode()))
//                            .findFirst()
//                            .orElse(null);
//                    if (product != null) {
//                        orderItem.setPrice(product.getPrice());
//                    }
//                    return orderItem;
//                })
//                .collect(Collectors.toList());
//
//        orderRepository.save(order);
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