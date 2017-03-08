package name.ukslim.heating.timer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import org.junit.Test;

public class ConstantProgrammeTest {
    
    @Test
    public void always_returns_configured_constant() {
        ConstantProgramme instance = new ConstantProgramme(Temperature.of(250));
        
        assertThat(instance.getTargetTemperature(LocalDateTime.of(2017, Month.APRIL, 15, 22, 10)), is(Optional.of(Temperature.of(250))));
    }
    
}
