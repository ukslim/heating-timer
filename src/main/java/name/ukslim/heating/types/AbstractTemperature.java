package name.ukslim.heating.types;

import org.immutables.value.Value;

@Value.Immutable
public abstract class AbstractTemperature {

    @Value.Parameter
    public abstract int deciCelcius();

}
