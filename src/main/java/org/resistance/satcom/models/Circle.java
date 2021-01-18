package org.resistance.satcom.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.javatuples.Pair;

import java.util.Optional;

@Data
@AllArgsConstructor
public class Circle {
    private Point center;
    private Double R;

    private static final int TRANSPOSE = 10000;

    public Double getX() {
        return center.getX();
    }

    public Double getY() {
        return center.getY();
    }

    /**
     * http://paulbourke.net/geometry/circlesphere/tvoght.c
     *
     * @param other circle to intersect with.
     * @return
     */
    public Optional<Pair<Point, Point>> getIntersectionPoints(Circle other, double epsilon) {
        double x0 = this.getX();
        double y0 = this.getY();
        double r0 = this.getR();
        double x1 = other.getX();
        double y1 = other.getY();
        double r1 = other.getR();


        double a, dx, dy, d, h, rx, ry;
        double x2, y2;

        /* dx and dy are the vertical and horizontal distances between
         * the circle centers.
         */
        dx = x1 - x0;
        dy = y1 - y0;

        /* Determine the straight-line distance between the centers. */
        //d = sqrt((dy*dy) + (dx*dx));
        d = Math.hypot(dx, dy); // Suggested by Keith Briggs

        /* Check for solvability. */
        if (Math.abs(d - (r0 + r1)) > epsilon) {
            /* no solution. circles do not intersect. */
            return Optional.empty();
        }
        if (d < Math.abs(r0 - r1)) {
            /* no solution. one circle is contained in the other */
            return Optional.empty();
        }

        /* 'point 2' is the point where the line through the circle
         * intersection points crosses the line between the circle
         * centers.
         */

        /* Determine the distance from point 0 to point 2. */
        a = ((r0 * r0) - (r1 * r1) + (d * d)) / (2.0 * d);

        /* Determine the coordinates of point 2. */
        x2 = x0 + (dx * a / d);
        y2 = y0 + (dy * a / d);

        /* Determine the distance from point 2 to either of the
         * intersection points.
         */
        h = Math.sqrt((r0 * r0) - (a * a));

        /* Now determine the offsets of the intersection points from
         * point 2.
         */
        rx = -dy * (h / d);
        ry = dx * (h / d);

        /* Determine the absolute intersection points. */
        return Optional.of(
                Pair.with(
                        new Point((x2 + (Double.isNaN(rx)? 0 : rx)  ), (y2 + (Double.isNaN(ry)? 0 : ry))),
                        new Point((x2 - (Double.isNaN(rx)? 0 : rx)), (y2 - (Double.isNaN(ry)? 0 : ry)))
                )
        );
    }

    public boolean intersect(Point point, double epsilon) {
        double dx = point.getX() - this.getX();
        double dy = point.getY() - this.getY();
        double d = Math.hypot(dx, dy);
        return Math.abs(d - this.getR()) < epsilon;
    }
}
