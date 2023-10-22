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
        if(driverRepository.count() > 0){
            Driver driver = new Driver();
            driver.setFirstName("Michiel");
            driver.setLastName("Van Loy");

            Driver driver2 = new Driver();
            driver.setFirstName("Bent");
            driver.setLastName("Melis");

            driverRepository.save(driver);
            driverRepository.save(driver2);
        }
    }

    @Transactional(readOnly = true)
    public List<DriverResponse> isInDriver(List<String> firstName) {

        return driverRepository.findByFirstName(firstName).stream()
                .map(driver ->
                        DriverResponse.builder()
                                .firstName(driver.getFirstName())
                                .lastName(driver.getLastName())
                                .build()
                ).toList();
    }
}
