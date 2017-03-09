package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import name.ukslim.heating.types.Temperature;

/**
 * <code>Programme</code> which always returns the same value.
 */
public class ConstantProgramme implements Programme {

    @JsonProperty
    private final Temperature constantTemp;

    @JsonCreator
    public ConstantProgramme(@JsonProperty("constantTemp") Temperature constantTemp) {
        this.constantTemp = constantTemp;
    }

    @Override
    public Optional<Temperature> getTargetTemperature(LocalDateTime dateTime) {
        return Optional.of(constantTemp);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.constantTemp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConstantProgramme other = (ConstantProgramme) obj;
        if (!Objects.equals(this.constantTemp, other.constantTemp)) {
            return false;
        }
        return true;
    }
    
    

}
