package base;

import static base.Main.arena;
import static base.Main.moveSpeed;

public class Player {

    private Ball ball;
    private Rectangle rec;

    public double speed = 0;
    public double dx = 0;
    public double dy = 0;

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
        this.move(this.dx*moveSpeed, this.dy*moveSpeed);
    }

    public double getXPosition() {
        return ball.getXPosition();
    }

    public double getYPosition() {
        return ball.getYPosition();
    }
}
