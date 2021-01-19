package org.resistance.satcom.services;

import org.resistance.satcom.models.Point;
import org.resistance.satcom.models.Satellite;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SatComProcessor {


    private final LocationService locationService;
    private final MessageService messageService;

    public SatComProcessor(LocationService locationService, MessageService messageService) {
        this.locationService = locationService;
        this.messageService = messageService;
    }

    public Optional<Point> getPointOFOrigin(List<Satellite> satellites) {
        var distances = satellites.stream()
                .sorted(Comparator.comparing(Satellite::getId))
                .map(satellite -> satellite.getDistance()).toArray(Double[]::new);
        return locationService.GetLocation(distances);
    }

    public Optional<String> getMessage(List<Satellite> satellites){
        var messages = satellites.stream().sorted(Comparator.comparing(Satellite::getDistance).reversed())
                .map(satellite -> satellite.getMessage()).collect(Collectors.toList());

        return messageService.getMessages(messages);
    }
}
