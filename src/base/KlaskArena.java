package base;

import org.w3c.dom.css.Rect;

import static base.Main.arena;

public class KlaskArena extends Rectangle {

    // *
    // GRAPHICAL ARENA
    // *

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public KlaskArena(double x, double y, double w, double h, String col, int layer) {
        super(x, y, w, h, col, layer);
        arena.addRectangle(this);
        // sides of klask arena
        arena.addRectangle(new Rectangle(x-20, y-20, w+40, 20, "BLACK1", -2));
        arena.addRectangle(new Rectangle(x-20, y-20, 20, h+40, "BLACK1", -2));
        arena.addRectangle(new Rectangle(x-20, y+h, w+40, 20, "BLACK1", -2));
        arena.addRectangle(new Rectangle(x+w, y-20, 20, h+40, "BLACK1", -2));
        // background
        arena.addRectangle(new Rectangle(0, 0, 800, 80, "BLACK1.5", -1));
        arena.addRectangle(new Rectangle(0, 0, 30, 500, "BLACK1.5", -1));
        arena.addRectangle(new Rectangle(0, y+h+20, 800, 30, "BLACK1.5", -1));
        arena.addRectangle(new Rectangle(x+w+20, 0, 30, 500, "BLACK1.5", -1));
        // create design on board
        arena.addBall(new Ball(50, 450, 200, "BLUE2", -3));
        arena.addBall(new Ball(50, 100, 200, "BLUE2", -3));
        arena.addBall(new Ball(750, 100, 200, "BLUE2", -3));
        arena.addBall(new Ball(750, 450, 200, "BLUE2", -3));
        arena.addRectangle(new Rectangle(390, 100, 20, 350, "BLUE2", -3));
        // add holes
        arena.addBall(new Ball(100, 280, 60, "BLACK1", -1));
        arena.addBall(new Ball(700, 280, 60, "BLACK1", -1));
        arena.addBall(new Ball(701, 279, 65, "GRAY1", -2));
        arena.addBall(new Ball(99, 279, 65, "GRAY1", -2));
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //---------------------------------------------------------------------------------------------------------------
    // getters, setters
    //---------------------------------------------------------------------------------------------------------------
    public double getEndXPosition() {
        return getXPosition()+getWidth();
    }
    public double getEndYPosition() {
        return getYPosition()+getHeight();
    }
    public double getMidXPosition() { return (getEndXPosition() - getXPosition()) / 2 + getXPosition() ; }
}
