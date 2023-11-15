package fact.it.race.controller;

import fact.it.race.dto.RaceRequest;
import fact.it.race.dto.RaceResponse;
import fact.it.race.service.RaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/race")
@RequiredArgsConstructor
public class RaceController {

    private final RaceService raceService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String createRace(@RequestBody RaceRequest raceRequest) {
        boolean result = raceService.createRace(raceRequest);
        return (result ? "Race created successfully" : "Race creation failed");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RaceResponse> getAllRaces() {
        return raceService.getAllRaces();
    }
}