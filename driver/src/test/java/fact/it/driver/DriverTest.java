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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DriverTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    private Driver driver1;
    private Driver driver2;

    @BeforeEach
    void setUp() {
        driver1 = new Driver();
        driver1.setFirstName("Michiel");
        driver1.setLastName("Van Loy");

        driver2 = new Driver();
        driver2.setFirstName("Bent");
        driver2.setLastName("Melis");
    }

    @Test
    void isInDriver_shouldReturnDriversWithGivenFirstName() {
        // given
        List<String> firstName = List.of("Michiel", "Bent");
        List<Driver> drivers = List.of(driver1, driver2);
        when(driverRepository.findByFirstName(firstName)).thenReturn(drivers);

        // when
        List<DriverResponse> result = driverService.isInDriver(firstName);

        // then
        assertEquals(2, result.size());
        assertEquals("Michiel", result.get(0).getFirstName());
        assertEquals("Van Loy", result.get(0).getLastName());
        assertEquals("Bent", result.get(1).getFirstName());
        assertEquals("Melis", result.get(1).getLastName());
    }
}
