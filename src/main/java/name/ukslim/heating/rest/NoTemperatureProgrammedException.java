package name.ukslim.heating.rest;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoTemperatureProgrammedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    NoTemperatureProgrammedException(LocalDateTime dateTime) {
        super("No temperature programmed for " + dateTime);
    }
}
