package name.ukslim.heating.timer;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.function.Predicate.isEqual;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class FiveTwoProgrammeTest {

    private static final LocalDateTime ARBITRARY_DATETIME = LocalDateTime.of(2017, Month.MARCH, 13, 10, 12);
    private static final Optional<Temperature> THIRTY_DEGREES = Optional.of(Temperature.of(300));

    @Rule 
    public MockitoRule mockito = MockitoJUnit.rule();
    
    @Mock
    private Programme weekdayProgramme;
    
    @Mock
    private Programme weekendProgramme;
    
    private FiveTwoProgramme instance;

    @Before
    public void setUpFixture() {
        instance = new FiveTwoProgramme(weekdayProgramme, weekendProgramme);
    }
    
    @Test
    public void delegates_to_weekday_when_date_is_weekday() {
        Stream.of(DayOfWeek.values())
                .filter(isEqual(SUNDAY).or(isEqual(SATURDAY)).negate())
                .map(dayOfWeek -> ARBITRARY_DATETIME.with(TemporalAdjusters.next(dayOfWeek)))
                .forEach( weekdayTime -> {
                    given(weekdayProgramme.getTargetTemperature(weekdayTime)).willReturn(THIRTY_DEGREES);
                    assertThat(instance.getTargetTemperature(weekdayTime), is(THIRTY_DEGREES));
                });
    }
    
    @Test
    public void delegates_to_weekend_when_date_is_Sunday() {
        LocalDateTime aTimeOnASunday = ARBITRARY_DATETIME.with(TemporalAdjusters.next(SUNDAY));
        given(weekendProgramme.getTargetTemperature(aTimeOnASunday)).willReturn(THIRTY_DEGREES);
        assertThat(instance.getTargetTemperature(aTimeOnASunday), is(THIRTY_DEGREES));
    }
    
    @Test
    public void delegates_to_weekend_when_date_is_Saturday() {
        LocalDateTime aTimeOnASaturday = ARBITRARY_DATETIME.with(TemporalAdjusters.next(SATURDAY));
        given(weekendProgramme.getTargetTemperature(aTimeOnASaturday)).willReturn(THIRTY_DEGREES);
        assertThat(instance.getTargetTemperature(aTimeOnASaturday), is(THIRTY_DEGREES));
    }
    
}
