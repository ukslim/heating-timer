package name.ukslim.heating.types;

import java.time.LocalTime;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@JsonSerialize(as = TimeTempPair.class)
@JsonDeserialize(as = TimeTempPair.class)
public abstract class AbstractTimeTempPair {

    @Value.Parameter
    public abstract LocalTime time();

    @Value.Parameter
    public abstract Temperature temperature();
}
