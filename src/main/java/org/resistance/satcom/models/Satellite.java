package org.resistance.satcom.models;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Satellite {
    private String name;
    private Double distance;
    private List<String> message;


    public Integer getId() {
        return name.equalsIgnoreCase("kenobi") ? 0 : name.equalsIgnoreCase("skywalker")? 1 : 2;
    }
}
