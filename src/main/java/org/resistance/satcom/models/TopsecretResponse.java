package org.resistance.satcom.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TopsecretResponse {
    Point position;
    String message;
}
