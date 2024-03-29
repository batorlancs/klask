package base;

import static base.Main.*;
import static base.Main.klaskArena;

public class Magnet extends Ball {

    // *
    // MAGNETS
    // *

    public double speed = 0;
    private double diameter = 0;
    private double dx = 0; // direction X
    private double dy = 0; // direction Y
    private boolean isAttached = false; // is it attached to a player
    private int inHole = 0; // is it in a hole: if 0: not in hole, 1: in hole1, 2: in hole2

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public Magnet(double x, double y, double diameter, String col) {
        super(x, y, diameter, col);
        this.diameter = diameter;
        arena.addBall(this);
    }

    public Magnet(double x, double y, double diameter, String col, int layer) {
        super(x, y, diameter, col, layer);
        arena.addBall(this);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //---------------------------------------------------------------------------------------------------------------
    // move the magnet in its direction and speed
    //---------------------------------------------------------------------------------------------------------------
    public void move() {
        if (isAttached) return; // if it is attached or in a hole don't move
        // bounce off the walls of the arena
        if (getXPosition()-15 <= klaskArena.getXPosition()) setDx(Math.abs(getDx()));
        if (getXPosition()+15 >= klaskArena.getEndXPosition()) setDx(-Math.abs(getDx()));
        if (getYPosition()-15 <= klaskArena.getYPosition()) setDy(Math.abs(getDx()));
        if (getYPosition()+15 >= klaskArena.getEndYPosition()) setDy(-Math.abs(getDy()));
        super.move(dx*this.speed*deltaTime, dy*this.speed*deltaTime);
        this.speed /= 1.000002; // slow down
    }

    //---------------------------------------------------------------------------------------------------------------
    // move the magnet in the given direction (used for player attracting the magnet)
    //---------------------------------------------------------------------------------------------------------------
    public void move(double dx, double dy, double range) {
        if (isAttached) return; // if it is attached or in a hole don't move
        double attractionSpeed = 0.0000006 * (60 - range); // range: the closer the magnet is to the player the faster
        // bounce off the walls of the arena
        if (getXPosition()-15 <= klaskArena.getXPosition()) setDx(Math.abs(getDx()));
        if (getXPosition()+15 >= klaskArena.getEndXPosition()) setDx(-Math.abs(getDx()));
        if (getYPosition()-15 <= klaskArena.getYPosition()) setDy(Math.abs(getDx()));
        if (getYPosition()+15 >= klaskArena.getEndYPosition()) setDy(-Math.abs(getDy()));
        super.move(dx*attractionSpeed*deltaTime, dy*attractionSpeed*deltaTime);
    }

    //---------------------------------------------------------------------------------------------------------------
    // set direction and speed of the magnet
    //---------------------------------------------------------------------------------------------------------------
    public void setForce(double dx, double dy, double speed) {
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }

    //---------------------------------------------------------------------------------------------------------------
    // setters, getters
    //---------------------------------------------------------------------------------------------------------------
    public void setDx(double dx) {
        this.dx = dx;
    }
    public double getDx() {
        return this.dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
    public double getDy() {
        return this.dy;
    }

    public double getDiameter() {
        return this.diameter;
    }

    public boolean isAttached() { return this.isAttached; }
    public void setAttached(boolean x) { this.isAttached = x; }

    public int getInHole() { return this.inHole; }
    public void setInHole(int inHole) { this.inHole = inHole; }
}
