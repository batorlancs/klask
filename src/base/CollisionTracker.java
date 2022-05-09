package base;

import static base.Main.*;

public class CollisionTracker {

    public void checkCollisions() {
        //player collision with game ball
        if (isInRange(p1, gball.getXPosition(), gball.getYPosition(), gball.getDiameter())) {
            //set direction and speed of the thing
            if (p1.getSpeed() == 0) gball.setForce(calcVector(p1, gball, 0), calcVector(p1, gball, 1), gball.getSpeed());
            else gball.setForce(calcVector(p1, gball, 0), calcVector(p1, gball, 1), 0.0001);
        }
        if (isInRange(p2, gball.getXPosition(), gball.getYPosition(), gball.getDiameter())) {
            //set direction and speed of the thing
            if (p2.getSpeed() == 0) gball.setForce(calcVector(p2, gball, 0), calcVector(p2, gball, 1), gball.getSpeed());
            else gball.setForce(calcVector(p2, gball, 0), calcVector(p2, gball, 1), 0.0001);
        }
        // player collides with hole
        if (isInRange(hole1, p1.getXPosition(), p1.getYPosition())) {
            if (!p1.isInHole()) {
                p1.setXPosition(hole1.getXPosition());
                p1.setYPosition(hole1.getYPosition());
                freezeGame();
                whoWon = 2;
            }
            p1.setInHole(true);
            //wonRound(2);
        }
        if (isInRange(hole2, p2.getXPosition(), p2.getYPosition())) {
            if (!p2.isInHole()) {
                p2.setXPosition(hole2.getXPosition());
                p2.setYPosition(hole2.getYPosition());
                freezeGame();
                whoWon = 1;
            }
            p2.setInHole(true);
            //wonRound(1);
        }
        // ball collides with hole
        if (isInRange(hole1, gball.getXPosition(), gball.getYPosition())) {
            if (gball.isInHole() == 0) {
                gball.move(calcVectorDia(hole1, gball.getXPosition(), gball.getYPosition(), 0, gball.getDiameter()), calcVectorDia(hole1, gball.getXPosition(), gball.getYPosition(), 1, gball.getDiameter()));
                freezeGame();
                whoWon = 2;
            }
            gball.setInHole(1);
            //wonRound(2);
        }
        if (isInRange(hole2, gball.getXPosition(), gball.getYPosition())) {
            if (gball.isInHole() == 0) {
                gball.move(calcVectorDia(hole2, gball.getXPosition(), gball.getYPosition(), 0, gball.getDiameter()), calcVectorDia(hole2, gball.getXPosition(), gball.getYPosition(), 1, gball.getDiameter()));
                freezeGame();
                whoWon = 1;
            }
            gball.setInHole(2);
            //wonRound(1);
        }
        // ball collides with magnets
        for (Magnet a: magnets) {
            if (!a.isAttached()) {
                // magnet collides with ball
                if (isInRange(gball, a.getXPosition(), a.getYPosition(), a.getDiameter())) {
                    a.setForce(calcVector(gball, a, 0), calcVector(gball, a, 1), gball.getSpeed());
                }
                // magnet collides with ball
                if (isInRange(p1, a.getXPosition(), a.getYPosition(), a.getDiameter())) {
                    // add magnet to player
                    p1.attachMagnet(a.getXPosition(), a.getYPosition());
                    a.setAttached(true);
                    arena.removeBall(a);
                }
                if (isInRange(p2, a.getXPosition(), a.getYPosition(), a.getDiameter())) {
                    // add magnet to player
                    p2.attachMagnet(a.getXPosition(), a.getYPosition());
                    a.setAttached(true);
                    arena.removeBall(a);
                }
                if (isInRange(hole1, a.getXPosition(), a.getYPosition())) {
                    a.move(calcVectorDia(hole1, a.getXPosition(), a.getYPosition(), 0, a.getDiameter()), calcVectorDia(hole1, a.getXPosition(), a.getYPosition(), 1, a.getDiameter()));
                    a.setAttached(true);
                }
                if (isInRange(hole2, a.getXPosition(), a.getYPosition())) {
                    a.move(calcVectorDia(hole2, a.getXPosition(), a.getYPosition(), 0, a.getDiameter()), calcVectorDia(hole2, a.getXPosition(), a.getYPosition(), 1, a.getDiameter()));
                    a.setAttached(true);
                }
            }
        }
    }

    public static double calcVectorDia(Ball hole, double x, double y, int which, double dia) {
        double xx = (hole.getXPosition() - x);
        double yy = (hole.getYPosition() - y);
        double r = Math.sqrt(xx*xx + yy*yy);
        if (which == 0) return xx*((30-dia/2)/r);
        return yy*((30-dia/2)/r);
    }

    private double calcVector(Player player, GameBall gameBall, int which) {
        double x = (gameBall.getXPosition() - player.getXPosition());
        double y = (gameBall.getYPosition() - player.getYPosition());
        double r = Math.sqrt(x*x + y*y);
        if (which == 0) return x*(1/r);
        return y*(1/r);
    }

    private double calcVector(GameBall gameBall, Magnet magnet, int which) {
        double x = (magnet.getXPosition() - gameBall.getXPosition());
        double y = (magnet.getYPosition() - gameBall.getYPosition());
        double r = Math.sqrt(x*x + y*y);
        if (which == 0) return x*(1/r);
        return y*(1/r);
    }

    public static boolean isInRangeDia(double x1, double y1, double x2, double y2, double dia) {
        double x = Math.abs(x2 - x1);
        double y = Math.abs(y2 - y1+5);
        return Math.sqrt(x*x + y*y) <= (30-dia/2);
    }

    private boolean isInRange(Ball hole, double x, double y) {
        double xx = Math.abs(hole.getXPosition() - x);
        double yy = Math.abs(hole.getYPosition() - y);
        return Math.sqrt(xx * xx + yy * yy) < (30);
    }

    private boolean isInRange(Player player, double x, double y, double dia) {
        double xx = Math.abs(player.getXPosition() - x);
        double yy = Math.abs(player.getYPosition() - y);
        return Math.sqrt(xx * xx + yy * yy) < (player.getDiameter()/2 + dia/2);
    }

    private boolean isInRange(GameBall gameBall, double x, double y, double dia) {
        double xx = Math.abs(gameBall.getXPosition() - x);
        double yy = Math.abs(gameBall.getYPosition() - y);
        return Math.sqrt(xx * xx + yy * yy) < (gameBall.getDiameter()/2 + dia/2);
    }
}

