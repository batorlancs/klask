package base;

import static base.Main.arena;
import static base.Main.scoreBoard;

public class ScoreBoard extends Text {

    private int points1 = 0;
    private int points2 = 0;

    public ScoreBoard(String text, int size, double x, double y, String col, int layer) {
        super(text, size, x, y, col, layer);
        arena.addText(this);
    }

    public ScoreBoard(String text, int size, double x, double y, String col) {
        super(text, size, x, y, col);
        arena.addText(this);
    }

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

    private void updateScore() {
        this.setText(points2 + " : " + points1);
    }
}
