package org.resistance.satcom.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum KnownSatellite {
    @JsonProperty("kenobi")
    KENOBI,
    @JsonProperty("skywalker")
    SKYWALKER,
    @JsonProperty("sato")
    SATO
}
