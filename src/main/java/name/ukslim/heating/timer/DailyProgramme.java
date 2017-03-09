package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Iterables;

import name.ukslim.heating.types.Temperature;
import name.ukslim.heating.types.TimeTempPair;

/**
 * <code>Programme</code> that encapsulating a daily schedule. That is, 
 * it's programmed with a set of control points based on time of day,
 * and ignores the date part of a request.
 */
public class DailyProgramme implements Programme {

    @JsonProperty
    private final List<TimeTempPair> controlPoints;
    private final Temperature lastControlpointOfDayTemp;

    @JsonCreator
    public DailyProgramme(@JsonProperty("controlPoints") List<TimeTempPair> controlPoints) {
        this.controlPoints =
                controlPoints.stream().sorted(Comparator.comparing(TimeTempPair::time).reversed()).collect(Collectors.toList());
        this.lastControlpointOfDayTemp = Iterables.getLast(controlPoints).temperature();
        
    }

    @Override
    public Optional<Temperature> getTargetTemperature(LocalDateTime targetDateTime) {
        return Optional.of(_getTargetTemperature(targetDateTime));
    }
    
    public Temperature _getTargetTemperature(LocalDateTime targetDateTime) {
        LocalTime targetTime = targetDateTime.toLocalTime();
        return controlPoints.stream()
                .filter( p -> ! p.time().isAfter(targetTime))
                .map(p -> p.temperature())
                .findFirst()
                .orElse(lastControlpointOfDayTemp);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.controlPoints);
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
        final DailyProgramme other = (DailyProgramme) obj;
        if (!Objects.equals(this.controlPoints, other.controlPoints)) {
            return false;
        }
        return true;
    }

    
    
}
