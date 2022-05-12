package base;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static base.Main.*;

public class ScoreBoard extends Text {

    // *
    // SCORE BOARD ABOVE THE ARENA
    // *

    private int points1 = 0; // points of player 1
    private int points2 = 0; // points of player 2

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ScoreBoard(String text, int size, double x, double y, String col, int layer) {
        super(text, size, x, y, col, layer);
        arena.addText(this);
    }

    public ScoreBoard(String text, int size, double x, double y, String col) {
        super(text, size, x, y, col);
        arena.addText(this);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //---------------------------------------------------------------------------------------------------------------
    // add one point to the winner who = 1 -> player 1, who = 2 -> player2
    //---------------------------------------------------------------------------------------------------------------
    public void wonPoint(int who) {
        if (who == 1) {
            points1++;
            if (points1 >= 6) {
                updateScore();
                infoTextR.setText(" you won!"); infoTextR.setColour("GREEN1");
                infoTextL.setText("you lost!"); infoTextL.setColour("RED1");
                freezeGame();
                whoWon = 0;
            } else updateScore();

        } else {
            points2++;
            if (points2 >= 6) {
                updateScore();
                infoTextR.setText("you lost!"); infoTextR.setColour("RED1");
                infoTextL.setText("you won!"); infoTextL.setColour("GREEN1");
                freezeGame();
                whoWon = 0;
            } else updateScore();
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // refresh the text
    //---------------------------------------------------------------------------------------------------------------
    private void updateScore() {
        this.setText(points2 + " : " + points1);
    }

    //---------------------------------------------------------------------------------------------------------------
    // reset the scoreboard
    //---------------------------------------------------------------------------------------------------------------
    public void reset() {
        this.setText("0 : 0");
        points1 = 0;
        points2 = 0;
    }

}
