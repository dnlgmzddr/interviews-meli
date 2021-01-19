package org.resistance.satcom.converters;

import org.resistance.satcom.models.KnownSatellite;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class KnownSatelliteConverter implements Converter<String, KnownSatellite> {

    @Override
    public KnownSatellite convert(String value) {
        return KnownSatellite.valueOf(value.toUpperCase());
    }
}