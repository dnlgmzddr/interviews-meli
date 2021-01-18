package org.resistance.satcom.config;

import org.resistance.satcom.services.LocationService;
import org.resistance.satcom.services.MessageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public LocationService locationService(){
        return new LocationService(0.01);
    }

    @Bean
    public MessageService messageService(){
        return new MessageService();
    }
}
