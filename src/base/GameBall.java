package base;

import static base.Main.arena;
import static base.Main.deltaTime;

public class GameBall extends Ball {

    private double speed = 0;
    private double diameter = 0;
    private double dx = 0;
    private double dy = 0;

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
        if (getXPosition()-15 <= 0 || getXPosition()+15 >= 800) setDx(-getDx());
        if (getYPosition()-15 <= 0 || getYPosition()+15 >= 500) setDy(-getDy());
        super.move(this.dx*this.speed*deltaTime, this.dy*this.speed*deltaTime);
        this.speed /= 1.000001;
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

}
