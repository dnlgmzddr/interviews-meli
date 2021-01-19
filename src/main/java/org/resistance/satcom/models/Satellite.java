package org.resistance.satcom.models;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Satellite {
    private KnownSatellite name;
    private Double distance;
    private List<String> message;


    public Integer getId() {
        return name.ordinal();
    }
}
