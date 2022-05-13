package base;

import static base.Main.*;
import static base.Main.magnets;

public class MovementManager {

    // *
    // KEEPS TRACK OF THE VECTOR AND SPEED OF OBJECTS AND MAKES A MOVE (PER FRAME)
    // *

    //---------------------------------------------------------------------------------------------------------------
    // update the vector and speed of all object
    //---------------------------------------------------------------------------------------------------------------
    public void setMoves() {
        // player 1 ----------------------------------------------------------------
        if (arena.upPressed()) p1.setDy(-1); // up
        if (arena.downPressed()) p1.setDy(1); // down
        if (!arena.upPressed() && !arena.downPressed()) p1.setDy(0);
        if (arena.leftPressed()) p1.setDx(-1); // left
        if (arena.rightPressed()) p1.setDx(1); // right
        if (!arena.leftPressed() && !arena.rightPressed()) p1.setDx(0);
        if (goingCross(1)) { //cross
            p1.setDx(p1.getDx() * 0.75);
            p1.setDy(p1.getDy() * 0.75);
        }
        if (isMoving(1)) p1.setSpeed(moveSpeed); //update speed
        else p1.setSpeed(0);
        // player 2 -----------------------------------------------------------------
        if (arena.wPressed()) p2.setDy(-1); // up
        if (arena.sPressed()) p2.setDy(1); // down
        if (!arena.wPressed() && !arena.sPressed()) p2.setDy(0);
        if (arena.aPressed()) p2.setDx(-1); // left
        if (arena.dPressed()) p2.setDx(1); // right
        if (!arena.aPressed() && !arena.dPressed()) p2.setDx(0);
        if (goingCross(2)) { // cross
            p2.setDx(p2.getDx() * 0.75);
            p2.setDy(p2.getDy() * 0.75);
        }
        if (isMoving(2)) p2.setSpeed(moveSpeed); // update speed
        else p2.setSpeed(0);
    }

    //---------------------------------------------------------------------------------------------------------------
    // make the move of all objects in their direction and speed
    //---------------------------------------------------------------------------------------------------------------
    public void makeMoves() {
        for (Magnet a: magnets) {
            a.move();
        }
        gball.move();
        p1.move();
        p2.move();
    }

    //---------------------------------------------------------------------------------------------------------------
    // check if the given player is going cross
    //---------------------------------------------------------------------------------------------------------------
    private boolean goingCross(int whichPlayer) {
        // player 1
        if (whichPlayer == 1) {
            if (arena.upPressed() || arena.downPressed()) {
                if (arena.leftPressed() || arena.rightPressed()) return true;
            }
            return false;
        }
        // player 2
        if (arena.wPressed() || arena.sPressed()) {
            if (arena.aPressed() || arena.dPressed()) return true;
        }
        return false;
    }

    //---------------------------------------------------------------------------------------------------------------
    // check if the given player is moving
    //---------------------------------------------------------------------------------------------------------------
    private boolean isMoving(int whichPlayer) {
        if (whichPlayer == 1)
            return (arena.upPressed() || arena.downPressed() || arena.leftPressed() || arena.rightPressed());
        return (arena.wPressed() || arena.sPressed() || arena.aPressed() || arena.dPressed());
    }
}
