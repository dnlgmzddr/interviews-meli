package org.resistance.satcom;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.resistance.satcom.controllers.TopSecretController;
import org.resistance.satcom.services.LocationService;
import org.resistance.satcom.services.MessageService;
import org.resistance.satcom.services.SatComProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.resistance.satcom.config.ServiceConfig.EPSILON;


@EnableWebMvc
@Configuration
public class SpringTestConfig {

    @Bean
    public LocationService locationService(){
        return new LocationService(EPSILON);
    }

    @Bean
    public MessageService messageService(){
        return new MessageService();
    }

    @Bean
    public SatComProcessor satComProcessor(LocationService locationService, MessageService messageService){
        return new SatComProcessor(locationService, messageService);
    }

    @Bean
    public TopSecretController topSecretController(SatComProcessor satComProcessor){
        return new TopSecretController(satComProcessor);
    }
}
