package base;

import static base.Main.*;

public class Player {

    private Ball ball;
    private Rectangle rec;

    private double speed = 0;
    public double diameter = 40;
    private double dx = 0;
    private double dy = 0;

    public Player(int x, int y, String col) {
        ball = new Ball(x, y, 40, col);
        rec = new Rectangle(x, y, 20, 40, col);
        arena.addBall(ball);
        arena.addRectangle(rec);
    }

    public void move(double dx, double dy) {
        if (ball.getXPosition()+dx+20 <= 800 && ball.getXPosition()+dx-20 >=0 && ball.getYPosition()+dy+20 <= 500 && ball.getYPosition()+dy-20 >= 0) {
            ball.move(dx, dy);
            rec.move(dx, dy);
        }
    }

    public void move() {
        this.move(this.dx*moveSpeed*deltaTime, this.dy*moveSpeed*deltaTime);
    }

    public double getXPosition() {
        return ball.getXPosition();
    }

    public double getYPosition() {
        return ball.getYPosition();
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
