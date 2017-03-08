package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * A <code>Programme</code> that delegates to weekend or weekday Programmes
 * depending on the target date
 */
public class FiveTwoProgramme implements Programme {

    private final Programme weekendProgramme;
    private final Programme weekdayProgramme;

    FiveTwoProgramme(Programme weekdayProgramme, Programme weekendProgramme) {
        this.weekdayProgramme = weekdayProgramme;
        this.weekendProgramme = weekendProgramme;
    }

    @Override
    public Optional<Temperature> getTargetTemperature(LocalDateTime dateTime) {
        switch(dateTime.getDayOfWeek()) {
            case SUNDAY:
            case SATURDAY:
                return weekendProgramme.getTargetTemperature(dateTime);
            default:
                return weekdayProgramme.getTargetTemperature(dateTime);
        }
    }

}
