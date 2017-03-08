package name.ukslim.heating.timer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import name.ukslim.heating.types.Temperature;
import name.ukslim.heating.types.TimeTempPair;

public class DailyProgrammeTest {

    private static final LocalDate ARBITRARY_DATE = LocalDate.of(2017, Month.MARCH, 13);
    private final static List<TimeTempPair> CONTROL_POINTS = ImmutableList.of(
                TimeTempPair.of(LocalTime.of(10, 15), Temperature.of(210)),
                TimeTempPair.of(LocalTime.of(11, 20), Temperature.of(150))
    );
    final Programme dailyProgramme = new DailyProgramme(CONTROL_POINTS);
    
    @Test
    public void gets_target_temperature_between_first_and_second_control_points_of_day() {
        final Optional<Temperature> actual = dailyProgramme.getTargetTemperature(LocalDateTime.of(ARBITRARY_DATE, LocalTime.of(10, 25)));
        
        assertThat(actual, is(Optional.of(Temperature.of(210))));
    }
    
    @Test
    public void gets_target_temperature_after_last_control_point_of_day() {
        final Optional<Temperature> actual = dailyProgramme.getTargetTemperature(LocalDateTime.of(ARBITRARY_DATE, LocalTime.of(11, 25)));
        
        assertThat(actual, is(Optional.of(Temperature.of(150))));
    }
    
    @Test
    public void gets_target_temperature_before_first_control_point_of_day() {
        final Optional<Temperature> actual = dailyProgramme.getTargetTemperature(LocalDateTime.of(ARBITRARY_DATE, LocalTime.of(7, 10)));
        
        assertThat(actual, is(Optional.of(Temperature.of(150))));
    }

}
