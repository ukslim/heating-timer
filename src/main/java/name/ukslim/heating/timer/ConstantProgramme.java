package name.ukslim.heating.timer;

import java.time.LocalDateTime;
import java.util.Optional;

import name.ukslim.heating.types.Temperature;

public class ConstantProgramme implements Programme {

    private final Temperature constantTemp;

    public ConstantProgramme(Temperature constantTemp) {
        this.constantTemp = constantTemp;
    }

    @Override
    public Optional<Temperature> getTargetTemperature(LocalDateTime dateTime) {
        return Optional.of(constantTemp);
    }

}
