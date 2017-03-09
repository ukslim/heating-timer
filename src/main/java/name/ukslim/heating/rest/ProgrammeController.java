package name.ukslim.heating.rest;


import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import name.ukslim.heating.timer.Programme;
import name.ukslim.heating.types.Temperature;

@RestController
public class ProgrammeController {

    private final Programme programme;
    
    @Autowired
    public ProgrammeController(Programme programme) {
        this.programme = programme;
    }
    
    @RequestMapping(method = GET, value = "heating/room/targetTemp")
    public Temperature temperature(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return programme
                .getTargetTemperature(dateTime)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "No temperature programmed for " + dateTime));
    }
}
