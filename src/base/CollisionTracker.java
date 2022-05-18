package base;

import static base.Main.*;

public class CollisionTracker {

    // *
    // KEEPS TRACK OF ALL COLLISIONS OF THE OBJECTS: PLAYER, MAGNET, GAME BALL, HOLE, BALL
    // *

    //---------------------------------------------------------------------------------------------------------------
    // freeze game for the given time before next round
    //---------------------------------------------------------------------------------------------------------------
    public void checkCollisions() {

        // player 1 collision with game ball
        //---------------------------------------------------------------------------------------------------------------
        if (isInRange(p1, gball.getXPosition(), gball.getYPosition(), gball.getDiameter())) {
            if (p1.getSpeed() == 0) gball.setForce(calcVector(p1, gball, 0), calcVector(p1, gball, 1), gball.getSpeed());
            else gball.setForce(calcVector(p1, gball, 0), calcVector(p1, gball, 1), 0.000099);
        }
        // player 2 collision with game ball
        //---------------------------------------------------------------------------------------------------------------
        if (isInRange(p2, gball.getXPosition(), gball.getYPosition(), gball.getDiameter())) {
            if (p2.getSpeed() == 0) gball.setForce(calcVector(p2, gball, 0), calcVector(p2, gball, 1), gball.getSpeed());
            else gball.setForce(calcVector(p2, gball, 0), calcVector(p2, gball, 1), 0.000099);
        }
        // player 1 collides with hole 1
        //---------------------------------------------------------------------------------------------------------------
        if (isInRange(hole1, p1.getXPosition(), p1.getYPosition())) {
            if (!p1.isInHole()) {
                deleteMagnetsInHole(1); // if magnet is in the hole delete magnet
                p1.setXPosition(hole1.getXPosition());
                p1.setYPosition(hole1.getYPosition());
                freezeGame();
                whoWon = 2;
                iTextManager.message(whoWon);
            }
            p1.setInHole(true);
        }
        // player 2 collides with hole 2
        //---------------------------------------------------------------------------------------------------------------
        if (isInRange(hole2, p2.getXPosition(), p2.getYPosition())) {
            if (!p2.isInHole()) {
                deleteMagnetsInHole(2); // if magnet is in the hole delete magnet
                p2.setXPosition(hole2.getXPosition());
                p2.setYPosition(hole2.getYPosition());
                freezeGame();
                whoWon = 1;
                iTextManager.message(whoWon);
            }
            p2.setInHole(true);
        }
        // hole 1 collides with ball
        //---------------------------------------------------------------------------------------------------------------
        if (isInRange(hole1, gball.getXPosition(), gball.getYPosition())) {
            if (gball.isInHole() == 0) {
                deleteMagnetsInHole(1); // if magnet is in the hole delete magnet
                gball.move(calcVectorDia(hole1, gball.getXPosition(), gball.getYPosition(), 0, gball.getDiameter()), calcVectorDia(hole1, gball.getXPosition(), gball.getYPosition(), 1, gball.getDiameter()));
                freezeGame();
                whoWon = 2;
                iTextManager.message(whoWon);
            }
            gball.setInHole(1);
        }
        // hole 2 collides with ball
        //---------------------------------------------------------------------------------------------------------------
        if (isInRange(hole2, gball.getXPosition(), gball.getYPosition())) {
            if (gball.isInHole() == 0) {
                deleteMagnetsInHole(2); // if magnet is in the hole delete magnet
                gball.move(calcVectorDia(hole2, gball.getXPosition(), gball.getYPosition(), 0, gball.getDiameter()), calcVectorDia(hole2, gball.getXPosition(), gball.getYPosition(), 1, gball.getDiameter()));
                freezeGame();
                whoWon = 1;
                iTextManager.message(whoWon);
            }
            gball.setInHole(2);
        }
        // magnet collisions
        //---------------------------------------------------------------------------------------------------------------
        for (Magnet a: magnets) {
            if (!a.isAttached()) {
                // magnet collides with ball
                //---------------------------------------------------------------------------------------------------------------
                if (isInRange(gball, a.getXPosition(), a.getYPosition(), a.getDiameter())) {
                    a.setForce(calcVector(gball, a, 0), calcVector(gball, a, 1), gball.getSpeed());
                }
                // magnet is near player 1 (magnet attraction)
                //---------------------------------------------------------------------------------------------------------------
                if (isInRange(p1, 60, a)) {
                    a.move(calcVector(a, p1, 0), calcVector(a, p1, 1), calcVectorAbs(a, p1));
                }

                // magnet is near player 2 (magnet attraction)
                //---------------------------------------------------------------------------------------------------------------
                if (isInRange(p2, 60, a)) {
                    a.move(calcVector(a, p2, 0), calcVector(a, p2, 1), calcVectorAbs(a, p2));
                }

                // manet collides with player 1
                //---------------------------------------------------------------------------------------------------------------
                if (isInRange(p1, a.getXPosition(), a.getYPosition(), a.getDiameter())) {
                    // attach magnet to player
                    p1.attachMagnet(a.getXPosition(), a.getYPosition());
                    a.setAttached(true);
                    arena.removeBall(a);
                }
                // magnet collides with player 2
                //---------------------------------------------------------------------------------------------------------------
                if (isInRange(p2, a.getXPosition(), a.getYPosition(), a.getDiameter())) {
                    // attach magnet to player
                    p2.attachMagnet(a.getXPosition(), a.getYPosition());
                    a.setAttached(true);
                    arena.removeBall(a);
                }
                // magnet collides with hole 1
                //---------------------------------------------------------------------------------------------------------------
                if (isInRange(hole1, a.getXPosition(), a.getYPosition())) {
                    a.move(calcVectorDia(hole1, a.getXPosition(), a.getYPosition(), 0, a.getDiameter()), calcVectorDia(hole1, a.getXPosition(), a.getYPosition(), 1, a.getDiameter()));
                    a.setInHole(1);
                    a.setAttached(true);
                }
                // magnet collides with hole 2
                //---------------------------------------------------------------------------------------------------------------
                if (isInRange(hole2, a.getXPosition(), a.getYPosition())) {
                    a.move(calcVectorDia(hole2, a.getXPosition(), a.getYPosition(), 0, a.getDiameter()), calcVectorDia(hole2, a.getXPosition(), a.getYPosition(), 1, a.getDiameter()));
                    a.setInHole(2);
                    a.setAttached(true);
                }
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // calculate the x or y (int which) axis of the vector between two points
    //---------------------------------------------------------------------------------------------------------------
    // calculate the absolute range between A and B points
    public static double calcVectorAbs(Ball ball, Player player) {
        double x = (player.getXPosition() - ball.getXPosition());
        double y = (player.getYPosition() - ball.getYPosition());
        return Math.sqrt(x*x + y*y);
    }
    // between two points
    public static double calcVector(Ball ball, Player player, int which) {
        double x = (player.getXPosition() - ball.getXPosition());
        double y = (player.getYPosition() - ball.getYPosition());
        double r = Math.sqrt(x*x + y*y);
        if (which == 0) return x*(1/r);
        return y*(1/r);
    }
    // hole and a circle
    public static double calcVectorDia(Ball hole, double x, double y, int which, double dia) {
        double xx = (hole.getXPosition() - x);
        double yy = (hole.getYPosition() - y);
        double r = Math.sqrt(xx*xx + yy*yy);
        if (which == 0) return xx*((30-dia/2)/r);
        return yy*((30-dia/2)/r);
    }

    // player to a ball
    private double calcVector(Player player, Ball gameBall, int which) {
        double x = (gameBall.getXPosition() - player.getXPosition());
        double y = (gameBall.getYPosition() - player.getYPosition());
        double r = Math.sqrt(x*x + y*y);
        if (which == 0) return x*(1/r);
        return y*(1/r);
    }

    // game ball and magnet
    private double calcVector(GameBall gameBall, Magnet magnet, int which) {
        double x = (magnet.getXPosition() - gameBall.getXPosition());
        double y = (magnet.getYPosition() - gameBall.getYPosition());
        double r = Math.sqrt(x*x + y*y);
        if (which == 0) return x*(1/r);
        return y*(1/r);
    }

    //---------------------------------------------------------------------------------------------------------------
    // check if two circles are in each other or a point is int the circle
    //---------------------------------------------------------------------------------------------------------------
    // hole and a circle are inside each other
    public static boolean isInRangeDia(double x1, double y1, double x2, double y2, double dia) {
        double x = Math.abs(x2 - x1);
        double y = Math.abs(y2 - y1+5);
        return Math.sqrt(x*x + y*y) <= (30-dia/2);
    }

    // ball and a point
    private boolean isInRange(Ball hole, double x, double y) {
        double xx = Math.abs(hole.getXPosition() - x);
        double yy = Math.abs(hole.getYPosition() - y);
        return Math.sqrt(xx * xx + yy * yy) < (30);
    }

    // player and a circle
    private boolean isInRange(Player player, double x, double y, double dia) {
        double xx = Math.abs(player.getXPosition() - x);
        double yy = Math.abs(player.getYPosition() - y);
        return Math.sqrt(xx * xx + yy * yy) < (player.getDiameter()/2 + dia/2);
    }

    // player with range and a ball
    private boolean isInRange(Player player, double playerDia, Ball ball) {
        double xx = Math.abs(player.getXPosition() - ball.getXPosition());
        double yy = Math.abs(player.getYPosition() - ball.getYPosition());
        return Math.sqrt(xx * xx + yy * yy) < (playerDia);
    }

    // game ball and circle
    private boolean isInRange(GameBall gameBall, double x, double y, double dia) {
        double xx = Math.abs(gameBall.getXPosition() - x);
        double yy = Math.abs(gameBall.getYPosition() - y);
        return Math.sqrt(xx * xx + yy * yy) < (gameBall.getDiameter()/2 + dia/2);
    }

    //---------------------------------------------------------------------------------------------------------------
    // check if two circles are in each other or a point is int the circle
    //---------------------------------------------------------------------------------------------------------------
    private void deleteMagnetsInHole(int whichHole) {
        for (Magnet a: magnets) {
            if (a.getInHole() == whichHole) {
                arena.removeBall(a);
            }
        }
    }
}

