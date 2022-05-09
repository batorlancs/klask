package base;

import static base.Main.*;

public class CollisionTracker {

    public void checkCollisions() {
        //player collision with game ball
        if (isInRange(p1, gball.getXPosition(), gball.getYPosition(), gball.getDiameter())) {
            System.out.println("collision: " + calcVector(p1, gball, 0) + ", " + calcVector(p1, gball, 1));
            //set direction and speed of the thing
            gball.setForce(calcVector(p1, gball, 0), calcVector(p1, gball, 1), 0.0001);
        }
        if (isInRange(p2, gball.getXPosition(), gball.getYPosition(), gball.getDiameter())) {
            System.out.println("collision: " + calcVector(p2, gball, 0) + ", " + calcVector(p2, gball, 1));
            //set direction and speed of the thing
            gball.setForce(calcVector(p2, gball, 0), calcVector(p2, gball, 1), 0.0001);
        }
        // ball collides with magnets
        for (Magnet a: magnets) {
            if (isInRange(gball, a.getXPosition(), a.getYPosition(), a.getDiameter())) {
                System.out.println("p1 is colliding with magnet");
            }
        }
        //player collision with magnet
    }

    private double calcVector(Player player, GameBall gameBall, int which) {
        double x = (gameBall.getXPosition() - player.getXPosition());
        double y = (gameBall.getYPosition() - player.getYPosition());
        double r = Math.sqrt(x*x + y*y);
        if (which == 0) return x*(1/r);
        return y*(1/r);
    }
    
    private boolean isInRange(Player player, double x, double y, double dia) {
        double xx = Math.abs(player.getXPosition() - x);
        double yy = Math.abs(player.getYPosition() - y);
        return Math.sqrt(xx * xx + yy * yy) < (20 + dia/2);
    }

    private boolean isInRange(GameBall gameBall, double x, double y, double dia) {
        double xx = Math.abs(gameBall.getXPosition() - x);
        double yy = Math.abs(gameBall.getYPosition() - y);
        return Math.sqrt(xx * xx + yy * yy) < (20 + dia/2);
    }
}

