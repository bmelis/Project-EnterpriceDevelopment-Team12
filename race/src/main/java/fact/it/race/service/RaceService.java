package fact.it.race.service;

import fact.it.race.dto.*;
import fact.it.race.model.Race;
import fact.it.race.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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

    public CircuitResponse getCircuit(int id) {
        return webClient.get()
                .uri("http://" + circuitServiceBaseUrl + "/api/circuit/" + id)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Circuit not found")))
                .bodyToMono(CircuitResponse.class)
                .block();
    }

    public TeamResponse getTeam(int id) {
        return webClient.get()
                .uri("http://" + teamServiceBaseUrl + "/api/team/" + id)
                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        clientResponse -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "team not found")))
                .bodyToMono(TeamResponse.class)
                .block();
    }

    public RaceResponse createRace(RaceRequest raceRequest) {
        CircuitResponse circuit = getCircuit(raceRequest.getCircuitId());
        TeamResponse team = getTeam(raceRequest.getTeamId());

        Race race = Race.builder()
                .raceName(raceRequest.getRaceName())
                .raceDate(raceRequest.getRaceDate())
                .teamId(team.getId())
                .circuitId(circuit.getId())
                .build();
        return mapToRaceResponse(race);
    }

    public void deleteRace(int id){
        try{
            raceRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new NoSuchElementException("Race with ID " + id + " not found");
        }
    }
    public RaceResponse updateRace(RaceRequest raceRequest, int id) {
        CircuitResponse circuit = getCircuit(raceRequest.getCircuitId());
        TeamResponse team = getTeam(raceRequest.getTeamId());
        Optional<Race> optionalRace  = raceRepository.findById(id);

        if (optionalRace.isPresent()) {
            Race race = optionalRace.get();

            race.setRaceDate(raceRequest.getRaceDate());
            race.setRaceName(raceRequest.getRaceName());
            race.setTeamId(team.getId());
            race.setCircuitId(circuit.getId());

            return mapToRaceResponse(race);
        } else {
            throw new NoSuchElementException("Race with ID " + id + " not found");
        }
    }


    public List<RaceResponse> getAllRaces() {
        List<Race> races = raceRepository.findAll();

        return races.stream().map(this::mapToRaceResponse).toList();
    }
    public RaceResponse findById(int id) {
        Optional<Race> optionalRace = raceRepository.findById(id);
        if(optionalRace.isPresent()) {
            Race race = optionalRace.get();
            return mapToRaceResponse(race);
        } else {
            // Return a ResponseEntity with a 404 Not Found status
            throw new NoSuchElementException("Race with ID " + id + " not found");
        }
    }

    private RaceResponse mapToRaceResponse(Race race) {
        return RaceResponse.builder()
                .id(race.getId())
                .raceName(race.getRaceName())
                .raceDate(race.getRaceDate())
                .circuitId(race.getCircuitId())
                .teamId(race.getTeamId())
                .build();
    }
}