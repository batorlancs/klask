package base;

import java.util.HashSet;

import static base.CollisionTracker.isInRangeDia;
import static base.Main.*;

public class Player {

    // *
    // PLAYER
    // *

    public int index = 0; // which player is it
    private Ball ball;
    private Ball ball2;
    private HashSet<Ball> attachedMagnets = new HashSet<Ball>();
    private Rectangle rec;

    private double speed = 0;
    private double diameter = 40;
    private double dx = 0; // direction X
    private double dy = 0; // direction Y

    private double minX = 0; // minimum x move position
    private double maxX = 0; // maximum x move position

    private boolean isInHole = false;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Player(int x, int y, String col, int which) {
        ball = new Ball(x, y, 40, col);
        ball2 = new Ball(x, y+5, 40, "GRAY1");
        rec = new Rectangle(x-7.5, y-45, 15, 40, col, 1);
        arena.addBall(ball2);
        arena.addBall(ball);
        arena.addRectangle(rec);
        // only letting them go halfway in their space of arena
        if (which == 1) {
            minX = klaskArena.getMidXPosition();
            maxX = klaskArena.getEndXPosition();
        } else {
            minX = klaskArena.getXPosition();
            maxX = klaskArena.getMidXPosition();
        }
        index = which;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //---------------------------------------------------------------------------------------------------------------
    // remove player from the arena
    //---------------------------------------------------------------------------------------------------------------
    public void removeFromArena() {
        arena.removeBall(ball);
        arena.removeBall(ball2);
        arena.removeRectangle(rec);
        for (Ball a: attachedMagnets) {
            arena.removeBall(a);
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // move the player in the input direction
    //---------------------------------------------------------------------------------------------------------------
    public void move() {
        this.move(this.dx*speed*deltaTime, this.dy*speed*deltaTime);
    }

    public void move(double dx, double dy) {
        if (isInHole()) { // check if its in a hole
            // see which hole is it in
            Ball hole;
            if (index == 1) hole = hole1;
            else hole = hole2;
            // move inside the hole only
            if (isInRangeDia(hole.getXPosition(), hole.getYPosition(), getXPosition()+dx, getYPosition()+dy, getDiameter())) {
                ball.move(dx, dy);
                ball2.move(dx, dy);
                rec.move(dx, dy);
                for (Ball a: attachedMagnets) {
                    a.move(dx, dy);
                }
            }
        } else { // if it is not in a hole
            if (ball.getXPosition()+dx+20 <= maxX && ball.getXPosition()+dx-20 >= minX && ball.getYPosition()+dy+20 <= klaskArena.getEndYPosition() && ball.getYPosition()+dy-20 >= klaskArena.getYPosition()) {
                ball.move(dx, dy);
                ball2.move(dx, dy);
                rec.move(dx, dy);
                for (Ball a: attachedMagnets) {
                    a.move(dx, dy);
                }
            }
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // attaching magnet if collision happens
    //---------------------------------------------------------------------------------------------------------------
    public void attachMagnet(double x, double y) {
        Ball newBall = new Ball(calcMagnetPosition(x, y, 0), calcMagnetPosition(x, y, 1), 20, "WHITE1");
        attachedMagnets.add(newBall);
        arena.addBall(newBall);
        if (attachedMagnets.size() >= 2) {
            if (index == 1) {
                iTextManager.message(2);
                whoWon = 2;
            }
            else {
                iTextManager.message(1);
                whoWon = 1;
            }
            freezeGame();
        }
    }

    private double calcMagnetPosition(double inx, double iny, int which) {
        double x = (inx - getXPosition());
        double y = (iny - getYPosition());
        double r = Math.sqrt(x*x + y*y);
        if (which == 0) return getXPosition() + (x*4/5);
        return getYPosition() + (y*4/5);
    }

    //---------------------------------------------------------------------------------------------------------------
    // set x or y position of the object (move all of its children parts/objects)
    //---------------------------------------------------------------------------------------------------------------
    public void setXPosition(double x) {
        for (Ball a: attachedMagnets) a.setXPosition(a.getXPosition() - ball.getXPosition() + x);
        ball.setXPosition(x);
        ball2.setXPosition(x);
        rec.setXPosition(x-7.5);
    }
    public void setYPosition(double y) {
        for (Ball a: attachedMagnets) a.setYPosition(a.getYPosition() - ball.getYPosition() + y);
        ball.setYPosition(y);
        ball2.setYPosition(y+5);
        rec.setYPosition(y-45);
    }

    //---------------------------------------------------------------------------------------------------------------
    // setters, getters
    //---------------------------------------------------------------------------------------------------------------
    public double getXPosition() { return ball.getXPosition(); }
    public double getYPosition() { return ball.getYPosition(); }

    public void setDx(double dx) { this.dx = dx; }
    public double getDx() { return this.dx; }

    public void setDy(double dy) { this.dy = dy; }
    public double getDy() { return this.dy; }

    public double getSpeed() { return this.speed; }
    public void setSpeed(double speed) { this.speed = speed; }

    public double getDiameter() { return this.diameter; }

    public boolean isInHole() { return this.isInHole; }
    public void setInHole(boolean x) { this.isInHole = x; }
}
