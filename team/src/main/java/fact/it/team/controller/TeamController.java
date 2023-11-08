package fact.it.team.controller;

import fact.it.team.dto.TeamRequest;
import fact.it.team.dto.TeamResponse;
import fact.it.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    // http://localhost:8082/api/inventory?skuCode=tube6in&skuCode=beam10ft
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String createTeam(@RequestBody TeamRequest teamRequest) {
        boolean result = teamService.createTeam(teamRequest);
        return (result ? "Race created successfully" : "Race creation failed");
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TeamResponse> getAllRaces() {
        return teamService.getAllTeams();
    }
}
