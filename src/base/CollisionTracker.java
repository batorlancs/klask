package base;

import static base.Main.*;

public class CollisionTracker {

    public void checkCollisions() {
        //player collision with game ball

    }

    private boolean isInRange(double x1, double y1, double dia1, double x2, double y2, double dia2) {
        double xx = Math.abs(x2 - x1);
        double yy = Math.abs(y2 - y1);
        return Math.sqrt(xx * xx + yy * yy) < (dia1/2 + dia2/2);
    }
}

