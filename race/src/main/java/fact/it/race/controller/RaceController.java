package fact.it.race.controller;

import fact.it.race.dto.RaceRequest;
import fact.it.race.dto.RaceResponse;
import fact.it.race.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RaceResponse createRace(@RequestBody RaceRequest raceRequest) {
        return raceService.createRace(raceRequest);
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RaceResponse updateRace(@PathVariable int id,@RequestBody RaceRequest raceRequest) {
        return raceService.updateRace(raceRequest, id);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RaceResponse> getAllRaces() {
        return raceService.getAllRaces();
    }
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RaceResponse getRaceById(@PathVariable int id) {
        return raceService.findById(id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRace(@PathVariable int id) {
        raceService.deleteRace(id);
    }
}