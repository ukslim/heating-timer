package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.util.Optional;

public interface Programme {

    Optional<Temperature> getTargetTemperature(LocalDateTime dateTime);

}
