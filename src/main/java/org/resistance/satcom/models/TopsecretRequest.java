package org.resistance.satcom.models;

import lombok.*;

import java.util.List;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class TopsecretRequest {
    private List<Satellite> satellites;
}
