package base;

import static base.Main.arena;

public class Magnet extends Ball {

    public double speed = 0;
    public double dx = 0;
    public double dy = 0;

    public Magnet(double x, double y, double diameter, String col) {
        super(x, y, diameter, col);
        arena.addBall(this);
    }

    public Magnet(double x, double y, double diameter, String col, int layer) {
        super(x, y, diameter, col, layer);
        arena.addBall(this);
    }
}
