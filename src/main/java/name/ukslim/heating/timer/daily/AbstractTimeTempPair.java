package name.ukslim.heating.timer.daily;

import java.time.LocalTime;

import org.immutables.value.Value;

import name.ukslim.heating.timer.Temperature;

@Value.Immutable
public abstract class AbstractTimeTempPair {

    @Value.Parameter
    public abstract LocalTime getTime();

    @Value.Parameter
    public abstract Temperature getTemperature();
}
