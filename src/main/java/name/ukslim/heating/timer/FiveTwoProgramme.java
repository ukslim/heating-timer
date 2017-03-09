package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import name.ukslim.heating.types.Temperature;

/**
 * A <code>Programme</code> that delegates to weekend or weekday Programmes
 * depending on the target date.
 */
public class FiveTwoProgramme implements Programme {

    @JsonProperty
    private final Programme weekendProgramme;
    @JsonProperty
    private final Programme weekdayProgramme;

    @JsonCreator
    public FiveTwoProgramme(@JsonProperty("weekdayProgramme") Programme weekdayProgramme, @JsonProperty("weekendProgramme") Programme weekendProgramme) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.weekendProgramme);
        hash = 23 * hash + Objects.hashCode(this.weekdayProgramme);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FiveTwoProgramme other = (FiveTwoProgramme) obj;
        if (!Objects.equals(this.weekendProgramme, other.weekendProgramme)) {
            return false;
        }
        if (!Objects.equals(this.weekdayProgramme, other.weekdayProgramme)) {
            return false;
        }
        return true;
    }

    
    
}
