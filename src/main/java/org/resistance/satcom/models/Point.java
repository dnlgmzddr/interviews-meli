package org.resistance.satcom.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Point {
    private Double X;
    private Double Y;

    public Point transpose(double dimension) {
        return new Point(X +dimension, Y + dimension);
    }
}

