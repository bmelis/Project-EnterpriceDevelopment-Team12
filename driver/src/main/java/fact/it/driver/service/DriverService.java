package fact.it.driver.service;
import fact.it.driver.dto.DriverResponse;
import fact.it.driver.model.Driver;
import fact.it.driver.repository.DriverRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    @PostConstruct
    public void loadData() {
        Driver driver = new Driver();
        driver.setFirstName("Max");
        driver.setLastName("Verstappen");
        driver.setTeamId(1);

        Driver driver2 = new Driver();
        driver2.setFirstName("Sergio");
        driver2.setLastName("Perez");
        driver2.setTeamId(1);

        Driver driver3 = new Driver();
        driver3.setFirstName("Lewis");
        driver3.setLastName("Hamilton");
        driver3.setTeamId(2);

        Driver driver4 = new Driver();
        driver4.setFirstName("George");
        driver4.setLastName("Russel");
        driver4.setTeamId(2);

        Driver driver5 = new Driver();
        driver5.setFirstName("Carlos");
        driver5.setLastName("Sainz");
        driver5.setTeamId(3);

        Driver driver6 = new Driver();
        driver6.setFirstName("Charles");
        driver6.setLastName("Leclerc");
        driver6.setTeamId(3);

        driverRepository.save(driver);
        driverRepository.save(driver2);
        driverRepository.save(driver3);
        driverRepository.save(driver4);
        driverRepository.save(driver5);
        driverRepository.save(driver6);
    }

    public List<DriverResponse> findDriverByTeamId(int teamId) {
        return driverRepository.getDriversByTeamId(teamId).stream()
                .map(driver ->
                        DriverResponse.builder()
                                .firstName(driver.getFirstName())
                                .lastName(driver.getLastName())
                                .teamId(driver.getTeamId())
                                .build()
                ).toList();
    }
}
