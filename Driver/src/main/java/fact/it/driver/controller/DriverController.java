package fact.it.driver.controller;

import fact.it.driver.dto.DriverResponse;
import fact.it.driver.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DriverResponse> isInDriver
            (@RequestParam List<String> firstName) {
        return driverService.isInDriver(firstName);
    }
}
