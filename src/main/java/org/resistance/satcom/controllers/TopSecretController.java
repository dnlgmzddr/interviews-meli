package org.resistance.satcom.controllers;

import org.resistance.satcom.models.TopsecretRequest;
import org.resistance.satcom.models.TopsecretResponse;
import org.resistance.satcom.services.SatComProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/topsecret", produces = {"application/json"})
public class TopSecretController {


    private final SatComProcessor satComProcessor;

    public TopSecretController(SatComProcessor satComProcessor) {
        this.satComProcessor = satComProcessor;
    }

    @PostMapping
    public TopsecretResponse postMessage(@RequestBody TopsecretRequest topsecretRequest) {
        var satellites = topsecretRequest.getSatellites();
        var pointOfOrigin = satComProcessor.getPointOFOrigin(satellites);
        return pointOfOrigin.map(poo -> new TopsecretResponse(poo, satComProcessor.getMessage(satellites).get()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
