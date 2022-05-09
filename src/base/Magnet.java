package base;

import static base.Main.arena;

public class Magnet extends Ball {

    public double speed = 0;
    private double diameter = 0;
    private double dx = 0;
    private double dy = 0;

    public Magnet(double x, double y, double diameter, String col) {
        super(x, y, diameter, col);
        this.diameter = diameter;
        arena.addBall(this);
    }

    public Magnet(double x, double y, double diameter, String col, int layer) {
        super(x, y, diameter, col, layer);
        arena.addBall(this);
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
}
