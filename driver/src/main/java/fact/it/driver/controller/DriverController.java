package fact.it.driver.controller;

import fact.it.driver.dto.DriverResponse;
import fact.it.driver.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @GetMapping("/{teamId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DriverResponse> getDriversByTeamId(@PathVariable int teamId) {
        return driverService.findDriverByTeamId(teamId);
    }
}