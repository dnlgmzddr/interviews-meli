package org.resistance.satcom.services;

import lombok.AllArgsConstructor;
import org.resistance.satcom.models.Circle;
import org.resistance.satcom.models.Point;

import java.util.Optional;

@AllArgsConstructor
public class LocationService {

    private static final Point KENOBI = new Point(-500d, -200d);
    private static final Point SKYWALKER = new Point(100d, -100d);
    private static final Point SATO = new Point(500d, 100d);

    public Double EPSILON;

    /**
     * @param distances distance from the emitter of the message to each satellite
     * @return X and Y coordinates to the emitter.
     */
    public Optional<Point> GetLocation(Double... distances) {
        if (distances.length != 3) throw new IllegalArgumentException();


        return findIntersection(
                new Circle(KENOBI, distances[0]),
                new Circle(SKYWALKER, distances[1]),
                new Circle(SATO, distances[2])
        );
    }


    /**
     * https://stackoverflow.com/questions/19723641/find-intersecting-point-of-three-circles-programmatically
     *
     * @param c1
     * @param c2
     * @param c3
     * @return
     */
    private Optional<Point> findIntersection(Circle c1, Circle c2, Circle c3) {

        var interPoints = c1.getIntersactionPoints(c2);
        if(interPoints.isEmpty()) return Optional.empty();
        if(c3.intersect(interPoints.get().getValue0(), EPSILON)){
            return Optional.of(interPoints.get().getValue0());
        }else if(c3.intersect(interPoints.get().getValue1(), EPSILON)){
            return Optional.of(interPoints.get().getValue1());
        }
        return Optional.empty();

    }


}
