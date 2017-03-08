package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.util.Optional;

import name.ukslim.heating.types.Temperature;

/**
 * Interface supplying the target temperature for a point in time
 */
public interface Programme {

    Optional<Temperature> getTargetTemperature(LocalDateTime dateTime);

}
