package name.ukslim.heating.rest;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import name.ukslim.heating.timer.Programme;

@Configuration
public class RestConfiguration {

    @Value(value = "classpath:programme.json")
    private Resource programmeJson;
    
    @Bean
    public Programme programme() throws IOException {
        
        try (InputStream input = programmeJson.getInputStream()) {
            return objectMapper().readValue(input, Programme.class);
        }
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        return mapper;
    }
    
}
