package org.resistance.satcom.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocationServiceTest {

    private static final Double EPSILON = 0.01;

    @Test
    public void ValidPointOfOrigen() {
        var service = new LocationService(EPSILON);
        // the origin of the signal is 0,0
        var solution = service.GetLocation(538.516, 141.421, 509.901);
        Assertions.assertTrue(solution.isPresent());
        Assertions.assertTrue(Math.abs(solution.get().getX() - 0d) < EPSILON);
        Assertions.assertTrue(Math.abs(solution.get().getY() - 0d) < EPSILON);
    }

    @Test
    public void InValidPointOfOrigin(){
        var service = new LocationService(EPSILON);
        // the origin of the signal is 0,0
        var solution = service.GetLocation(100d, 115.5, 142.7);
        Assertions.assertTrue(solution.isEmpty());

    }

}
