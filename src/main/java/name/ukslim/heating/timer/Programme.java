package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import name.ukslim.heating.types.Temperature;

/**
 * Interface supplying the target temperature for a point in time
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type")
@JsonSubTypes({
    @Type(value = ConstantProgramme.class, name = "constant"),
    @Type(value = DailyProgramme.class, name = "daily"),
    @Type(value = FiveTwoProgramme.class, name = "fiveTwo")})
public interface Programme {

    Optional<Temperature> getTargetTemperature(LocalDateTime dateTime);

}
