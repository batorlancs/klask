package base;

import static base.Main.arena;

public class GameBall extends Ball {

    public double speed = 0;
    public double dx = 0;
    public double dy = 0;

    public GameBall(double x, double y, double diameter, String col) {
        super(x, y, diameter, col);
        arena.addBall(this);
    }

    public GameBall(double x, double y, double diameter, String col, int layer) {
        super(x, y, diameter, col, layer);
        arena.addBall(this);
    }

    public void setMovement(double dx, double dy, double speed) {
        this.dx = dx;
        this.dy = dy;
    }
}
