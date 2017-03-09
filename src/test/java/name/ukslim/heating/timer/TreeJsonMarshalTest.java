package name.ukslim.heating.timer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalTime;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;

import name.ukslim.heating.types.Temperature;
import name.ukslim.heating.types.TimeTempPair;

public class TreeJsonMarshalTest {

    private static final String jsonString = "{\"type\":\"fiveTwo\",\"weekdayProgramme\":{\"type\":\"constant\",\"constantTemp\":{\"deciCelcius\":100}},\"weekendProgramme\":{\"type\":\"daily\",\"controlPoints\":[{\"time\":[3,10],\"temperature\":{\"deciCelcius\":200}},{\"time\":[2,20],\"temperature\":{\"deciCelcius\":300}}]}}";
    
    private final ObjectMapper mapper;
    private final Programme p1 = new ConstantProgramme(Temperature.of(100));
    private final Programme p2 = new DailyProgramme(ImmutableList.of( 
            TimeTempPair.of(LocalTime.of(2, 20), Temperature.of(300)), 
            TimeTempPair.of(LocalTime.of(3,10), Temperature.of(200))));
    private final Programme p0 = new FiveTwoProgramme(p1, p2);

    public TreeJsonMarshalTest() {
        this.mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }
    
    @Test
    public void marshals_tree_of_programmes() throws JsonProcessingException {
        assertThat(mapper.writeValueAsString(p0), is(jsonString));
    }
    
    @Test
    public void unmarshalls_tree_of_programmes() throws Exception {
        assertThat(mapper.readValue(jsonString, Programme.class), is(p0));
    }
    
}
