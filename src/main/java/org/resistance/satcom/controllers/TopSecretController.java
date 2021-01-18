package org.resistance.satcom.controllers;

import org.resistance.satcom.models.Satellite;
import org.resistance.satcom.models.TopsecretRequest;
import org.resistance.satcom.models.TopsecretResponse;
import org.resistance.satcom.services.LocationService;
import org.resistance.satcom.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/topsecret", produces = {"application/json"})
public class TopSecretController {

    private LocationService locationService;
    private MessageService messageService;

    public TopSecretController(LocationService locationService, MessageService messageService) {
        this.locationService = locationService;
        this.messageService = messageService;
    }


    @PostMapping
    public TopsecretResponse postMessage(@RequestBody TopsecretRequest topsecretRequest) {

        var distances = topsecretRequest.getSatellites()
                .stream().sorted(Comparator.comparing(Satellite::getId))
                .map(satellite -> satellite.getDistance()).toArray(Double[]::new);

        var pointOfOrigin = locationService.GetLocation(distances);
        if (pointOfOrigin.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND
            );
        }

        var messages =  topsecretRequest.getSatellites()
                .stream().sorted(Comparator.comparing(Satellite::getDistance).reversed())
                .map(satellite -> satellite.getMessage()).collect(Collectors.toList());

        var message = messageService.getMessages(messages);

        return new TopsecretResponse(pointOfOrigin.get(), message.get());
    }
}
