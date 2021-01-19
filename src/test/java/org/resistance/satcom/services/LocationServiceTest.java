package org.resistance.satcom.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.resistance.satcom.models.Point;

public class LocationServiceTest {

    private static final Double EPSILON = 0.01;

    @Test
    public void ValidPointOfOrigen() {
        var poo = new Point(0d, -0d);
        var service = new LocationService(EPSILON);
        var solution = service.GetLocation(538.516, 141.421, 509.901);
        Assertions.assertTrue(solution.isPresent());
        Assertions.assertTrue(Math.abs(solution.get().getX() - poo.getX()) < EPSILON);
        Assertions.assertTrue(Math.abs(solution.get().getY() - poo.getY()) < EPSILON);
    }

    @Test
    public void ValidPointOfOrigen2() {
        var poo = new Point(100d, -100d);
        var service = new LocationService(EPSILON);
        var solution = service.GetLocation(608.276, 0d, 447.214);
        Assertions.assertTrue(solution.isPresent());
        Assertions.assertTrue(Math.abs(solution.get().getX() - poo.getX()) < EPSILON);
        Assertions.assertTrue(Math.abs(solution.get().getY() - poo.getY()) < EPSILON);
    }

    @Test
    public void ValidPointOfOrigen3() {
        var poo = new Point(200d, -100d);
        var service = new LocationService(EPSILON);
        var solution = service.GetLocation(707.107, 100d, 360.555);
        Assertions.assertTrue(solution.isPresent());
        Assertions.assertTrue(Math.abs(solution.get().getX() - poo.getX()) < EPSILON);
        Assertions.assertTrue(Math.abs(solution.get().getY() - poo.getY()) < EPSILON);
    }

    @Test
    public void InValidPointOfOrigin(){
        var service = new LocationService(EPSILON);
        // the origin of the signal is 0,0
        var solution = service.GetLocation(100d, 115.5, 142.7);
        Assertions.assertTrue(solution.isEmpty());

    }

}
