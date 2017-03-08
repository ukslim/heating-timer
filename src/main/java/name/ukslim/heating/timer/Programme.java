package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.util.Optional;

import name.ukslim.heating.types.Temperature;

public interface Programme {

    Optional<Temperature> getTargetTemperature(LocalDateTime dateTime);

}
