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
    public void testIsInDriver() {
        String firstName = "John";
        Driver driver = new Driver();
        driver.setFirstName("John");
        driver.setLastName("Doe");

        when(driverRepository.findByFirstName(firstName)).thenReturn(List.of(driver));

        List<DriverResponse> result = driverService.isInDriver(firstName);

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Doe", result.get(0).getLastName());
    }
}
