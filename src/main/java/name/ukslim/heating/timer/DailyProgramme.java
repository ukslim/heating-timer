package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Iterables;

import name.ukslim.heating.types.Temperature;
import name.ukslim.heating.types.TimeTempPair;

public class DailyProgramme implements Programme {

    private final List<TimeTempPair> controlPoints;
    private final Temperature lastControlpointOfDayTemp;

    public DailyProgramme(List<TimeTempPair> controlPoints) {
        this.controlPoints =
                controlPoints.stream().sorted(Comparator.comparing(TimeTempPair::getTime).reversed()).collect(Collectors.toList());
        this.lastControlpointOfDayTemp = Iterables.getLast(controlPoints).getTemperature();
        
    }

    @Override
    public Optional<Temperature> getTargetTemperature(LocalDateTime targetDateTime) {
        return Optional.of(_getTargetTemperature(targetDateTime));
    }
    
    public Temperature _getTargetTemperature(LocalDateTime targetDateTime) {
        LocalTime targetTime = targetDateTime.toLocalTime();
        return controlPoints.stream()
                .filter( p -> ! p.getTime().isAfter(targetTime))
                .map(p -> p.getTemperature())
                .findFirst()
                .orElse(lastControlpointOfDayTemp);
    }

}
