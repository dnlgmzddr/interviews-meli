package org.resistance.satcom;

import org.resistance.satcom.controllers.TopSecretController;
import org.resistance.satcom.services.LocationService;
import org.resistance.satcom.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@EnableWebMvc
@Configuration
public class SpringTestConfig {


    @Resource
    private HttpServletRequest request;

    @Autowired
    private RestTemplate http;

    @Bean
    public LocationService locationService(){
        return new LocationService(0.01);
    }

    @Bean
    public MessageService messageService(){
        return new MessageService();
    }

    @Bean
    public TopSecretController topSecretController(LocationService locationService, MessageService messageService){
        return new TopSecretController(locationService, messageService);
    }
}
