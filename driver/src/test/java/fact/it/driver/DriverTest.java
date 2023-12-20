package fact.it.driver;

import fact.it.driver.dto.DriverResponse;
import fact.it.driver.model.Driver;
import fact.it.driver.repository.DriverRepository;
import fact.it.driver.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @Test
    public void testGetByTeamId() {
        Driver driver = new Driver();
        driver.setFirstName("Max");
        driver.setLastName("Verstappen");
        driver.setTeamId(1);

        Driver driver2 = new Driver();
        driver2.setFirstName("Sergio");
        driver2.setLastName("Perez");
        driver2.setTeamId(1);

        when(driverRepository.getDriversByTeamId(1)).thenReturn(Arrays.asList(driver, driver2));


        List<DriverResponse> result = driverService.findDriverByTeamId(1);

        assertEquals("Max", result.get(0).getFirstName());
        assertEquals("Sergio", result.get(1).getFirstName());
    }
}
