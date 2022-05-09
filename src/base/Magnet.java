package base;

import static base.Main.*;
import static base.Main.klaskArena;

public class Magnet extends Ball {

    public double speed = 0;
    private double diameter = 0;
    private double dx = 0;
    private double dy = 0;
    private boolean isAttached = false;

    public Magnet(double x, double y, double diameter, String col) {
        super(x, y, diameter, col);
        this.diameter = diameter;
        arena.addBall(this);
    }

    public Magnet(double x, double y, double diameter, String col, int layer) {
        super(x, y, diameter, col, layer);
        arena.addBall(this);
    }

    public void move() {
        if (isAttached) return;
        if (getXPosition()-15 <= klaskArena.getXPosition()) setDx(Math.abs(getDx()));
        if (getXPosition()+15 >= klaskArena.getEndXPosition()) setDx(-Math.abs(getDx()));
        if (getYPosition()-15 <= klaskArena.getYPosition()) setDy(Math.abs(getDx()));
        if (getYPosition()+15 >= klaskArena.getEndYPosition()) setDy(-Math.abs(getDy()));
        super.move(dx*this.speed*deltaTime, dy*this.speed*deltaTime);
        this.speed /= 1.000002;
    }

    public void setForce(double dx, double dy, double speed) {
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }
    
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
}
