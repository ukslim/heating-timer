package name.ukslim.heating.timer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import name.ukslim.heating.types.Temperature;

public class ConstantProgrammeTest {
    
    ObjectMapper mapper = new ObjectMapper();
    
    @Test
    public void always_returns_configured_constant() {
        ConstantProgramme instance = new ConstantProgramme(Temperature.of(250));
        
        assertThat(instance.getTargetTemperature(LocalDateTime.of(2017, Month.APRIL, 15, 22, 10)), is(Optional.of(Temperature.of(250))));
    }
    
    @Test
    public void marshals_to_json() throws JsonProcessingException {
        ConstantProgramme instance = new ConstantProgramme(Temperature.of(250));
        
        assertThat(mapper.writeValueAsString(instance), is("{\"type\":\"constant\",\"constantTemp\":{\"deciCelcius\":250}}"));
    }
    
    @Test
    public void unmarshals_from_json() throws Exception {
        Programme programme = mapper.readValue("{\"type\":\"constant\",\"constantTemp\":{\"deciCelcius\":150}}", Programme.class);
        assertThat(programme, is(new ConstantProgramme(Temperature.of(150))));
    }
}
