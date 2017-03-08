package name.ukslim.heating.timer;

import org.immutables.value.Value;

@Value.Immutable
public abstract class AbstractTemperature {

    @Value.Parameter
    public abstract int deciCelcius();

}
