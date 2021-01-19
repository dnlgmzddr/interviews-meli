package org.resistance.satcom.controllers;

import org.resistance.satcom.models.KnownSatellite;
import org.resistance.satcom.models.Satellite;
import org.resistance.satcom.models.TopsecretResponse;
import org.resistance.satcom.services.SatComProcessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "//topsecret_split", produces = {"application/json"})
public class TopSecretSplitController {

    private static Map<KnownSatellite, Satellite> calls = new HashMap<>();

    private final SatComProcessor satComProcessor;

    public TopSecretSplitController(SatComProcessor satComProcessor) {
        this.satComProcessor = satComProcessor;
    }

    @PostMapping("{name}")
    public ResponseEntity postMessage(@PathVariable KnownSatellite name, @RequestBody Satellite request) {
        request.setName(name);
        calls.put(name, request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/")
    public TopsecretResponse getMessage() {
        if(calls.size() != 3){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        var satellites = calls.values().stream().collect(Collectors.toList());
        var pointOfOrigin = satComProcessor.getPointOFOrigin(satellites);
        return pointOfOrigin.map(poo -> new TopsecretResponse(poo, satComProcessor.getMessage(satellites).get()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
