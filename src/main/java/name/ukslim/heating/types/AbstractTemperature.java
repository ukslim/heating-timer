package name.ukslim.heating.types;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = Temperature.class)
@JsonDeserialize(as = Temperature.class)
public abstract class AbstractTemperature {

    @Value.Parameter
    public abstract int deciCelcius();

}
