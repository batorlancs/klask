package base;

import static base.Main.arena;

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
            if (points1 >= 6) return;
            updateScore();

        } else {
            points2++;
            if (points2 >= 6) return;
            updateScore();
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // refresh the text
    //---------------------------------------------------------------------------------------------------------------
    private void updateScore() {
        this.setText(points2 + " : " + points1);
    }
}
