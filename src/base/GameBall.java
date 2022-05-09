package base;

import static base.Main.*;

public class GameBall extends Ball {

    private double speed = 0;
    private double diameter = 0;
    private double dx = 0;
    private double dy = 0;
    private int isInHole = 0;

    public GameBall(double x, double y, double diameter, String col) {
        super(x, y, diameter, col);
        this.diameter = diameter;
        arena.addBall(this);
    }

    public GameBall(double x, double y, double diameter, String col, int layer) {
        super(x, y, diameter, col, layer);
        arena.addBall(this);
    }

    public void move() {
        //bounce off the wall
//        double newdx = this.dx*this.speed*deltaTime;
//        double newdy = this.dy*this.speed*deltaTime;
        if (isInHole != 0) {
            if (isInHole == 1) {

            }
            //super.move(this.dx*this.speed*deltaTime, this.dy*this.speed*deltaTime);
            this.speed /= 1.000001;
        }
        else {
            if (getXPosition()-15 <= klaskArena.getXPosition()) setDx(Math.abs(getDx()));
            if (getXPosition()+15 >= klaskArena.getEndXPosition()) setDx(-Math.abs(getDx()));
            if (getYPosition()-15 <= klaskArena.getYPosition()) setDy(Math.abs(getDx()));
            if (getYPosition()+15 >= klaskArena.getEndYPosition()) setDy(-Math.abs(getDy()));
            super.move(this.dx*this.speed*deltaTime, this.dy*this.speed*deltaTime);
            this.speed /= 1.000001;
        }
    }

    public void setForce(double dx, double dy, double speed) {
        this.dx = dx;
        this.dy = dy;
        this.speed = speed;
    }

    public double getDiameter() {
        return this.diameter;
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

    public double getSpeed() {
        return this.speed;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int isInHole() { return this.isInHole; }
    public void setInHole(int x) { this.isInHole = x; }

}
