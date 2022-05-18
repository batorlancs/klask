package base;

import static base.Main.arena;

public class ITextManager {

    // *
    // MANAGES ALL TEXTS EXCEPT THE SCOREBOARD
    // *

    private Text infoTextR; // right upper side of the screen shows messages
    private Text infoTextL; // left upper side of the screen shows messages

    private Text resetText; // press enter to reset the game (only appears when someone won)
    private Text resetTextShadow; // used for design shadow below resetText

    private final int maxLength = 24;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CONSTRUCTOR
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ITextManager() {
        // infoTexts
        infoTextR = new Text("", 20, 630, 60, "BLACK1");
        arena.addText(infoTextR);
        infoTextL = new Text("", 20, 60, 60, "BLACK1");
        arena.addText(infoTextL);
        // reset Text
        resetText = new Text("", 20, 260, 340, "WHITE1", 2); arena.addText(resetText);
        resetTextShadow = new Text("", 20, resetText.getXPosition(), resetText.getYPosition()+2, "BLACK1", 1);
        arena.addText(resetTextShadow);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //---------------------------------------------------------------------------------------------------------------
    // set reset text visible or invisible
    //---------------------------------------------------------------------------------------------------------------
    public void resetVisible(boolean x) {
        if (x) {
            resetText.setText("press 'enter' to reset game!");
            resetTextShadow.setText("press 'enter' to reset game!");
        } else {
            resetText.setText("");
            resetTextShadow.setText("");
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // show +1 point on the player's side who won the point (infoTextL or infoTextR)
    //---------------------------------------------------------------------------------------------------------------
    public void message(int winner) {
        if (winner == 1) {
            infoTextR.setText(" +1 point!"); infoTextR.setColour("GREEN1");
        } else {
            infoTextL.setText("+1 point!"); infoTextL.setColour("GREEN1");
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // when the game ends show who lost and won (using infoTextL and infoTextR)
    //---------------------------------------------------------------------------------------------------------------
    public void won(int winner) {
        if (winner == 1) { // if player 1 won
            infoTextR.setText("YOU WON!"); infoTextR.setColour("GREEN1");
            infoTextL.setText("YOU LOST!"); infoTextL.setColour("RED1");
        } else { // if player 2 won
            infoTextR.setText("YOU LOST!"); infoTextR.setColour("RED1");
            infoTextL.setText("YOU WON!"); infoTextL.setColour("GREEN1");
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // reset infoTexts if it is the end of the round (not the end of the game)
    //---------------------------------------------------------------------------------------------------------------
    public void clearRound() {
        if (!infoTextL.getText().equals("YOU LOST!") && !infoTextL.getText().equals("YOU WON!")) { // check if it is the end of game
            infoTextL.setText("");
            infoTextR.setText("");
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // reset infoTexts
    //---------------------------------------------------------------------------------------------------------------
    public void reset() {
        infoTextL.setText("");
        infoTextR.setText("");
    }

}
