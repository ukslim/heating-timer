package name.ukslim.heating.types;

import java.time.LocalTime;

import org.immutables.value.Value;

@Value.Immutable
public abstract class AbstractTimeTempPair {

    @Value.Parameter
    public abstract LocalTime getTime();

    @Value.Parameter
    public abstract Temperature getTemperature();
}
