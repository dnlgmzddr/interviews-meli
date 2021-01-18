package org.resistance.satcom.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.resistance.satcom.serializers.DoubleContextualSerializer;
import org.resistance.satcom.serializers.Precision;

@Data
@AllArgsConstructor
public class Point {

    @JsonProperty("x")
    @JsonSerialize(using = DoubleContextualSerializer.class)
    private Double X;

    @JsonProperty("y")
    @JsonSerialize(using = DoubleContextualSerializer.class)
    private Double Y;
}

