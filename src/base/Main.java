package base;

import java.awt.Color;
import java.util.Random;

public class Main {

    // *
    // MAIN FUNCTION
    // *

    public static GameArena arena; // game arena
    public static KlaskArena klaskArena; // the klask arena graphics, and its properties (positions)
    public static CollisionTracker collisionTracker; // tracks the collision of all objects needed for the game
    public static MovementManager movementManager; // tracks and updates the movement vector and speed of all objects

    public static Player p1; // player 1 (arrow keys, left side of arena)
    public static Player p2; // player 2 (wasd buttons, right side of arena)
    public static GameBall gball; // the yellow ball to score in the goal
    public static Magnet[] magnets = new Magnet[3]; // the white magnets attaching to players
    public static ScoreBoard scoreBoard; // the text shown above the arena
    public static Ball hole1; // goal of player 1
    public static Ball hole2; // goal of player 2

    public static final double moveSpeed = 0.00006; // movement speed of the player
    public static double deltaTime = 1;


    public static final int freezeTime = 100000;
    public static boolean frozen = false;
    public static double frozenCount = 0; // counter of time
    public static int whoWon = 0; // when player 1 wins it turns 1, when player 2 wins it turns 2, when nobody won its 0

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // GAME LOOP
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        Main main = new Main();
        main.resetObjects();

        double beginTime = Time.getTime();
        double endTime;

        while (true) {
            System.out.println("game is running");

            if (!frozen) {
                collisionTracker.checkCollisions(); // checks all the collisions and calls functions when collision happens
                movementManager.setMoves(); // calculates the moves of all objects for this frame
                movementManager.makeMoves(); // makes the moves for all objects
            }

            // deltatime for smooth movement and realistic time
            endTime = Time.getTime();
            deltaTime = (endTime - beginTime) / 100;
            beginTime = endTime;

            if (frozenCount > 0) {
                frozenCount -= deltaTime/100;
            } else if (frozen) {
                frozen = false;
                wonRound(whoWon);
                whoWon = 0;
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //---------------------------------------------------------------------------------------------------------------
    // if someone won: update the board, reset the position and properties of all objects
    //---------------------------------------------------------------------------------------------------------------
    public static void wonRound(int who) {
        scoreBoard.wonPoint(who); // update scoreboard
        resetRound(who); // reset objects
    }

    //---------------------------------------------------------------------------------------------------------------
    // reset all the objects for next round
    //---------------------------------------------------------------------------------------------------------------
    public static void resetRound(int who) {
        // remove objects from arena
        p1.removeFromArena();
        p2.removeFromArena();
        arena.removeBall(gball);
        // recall objects
        p1 = new Player(600, 280, "BLACK2", 1);
        p2 = new Player(200, 280, "BLACK2", 2);
        // magnets remove/recall
        for (int i = 0; i < 3; i++) {
            arena.removeBall(magnets[i]);
            magnets[i] = new Magnet(400, 175+i*100, 20, "WHITE1");
        }
        // set the ball in the losers corner (random out of 2)
        Random rand = new Random();
        if (who == 1) gball = new GameBall(100, 150 + rand.nextInt(2)*250, 30, "YELLOW1");
        else gball = new GameBall(700, 150 + rand.nextInt(2)*250, 30, "YELLOW1");
    }

    //---------------------------------------------------------------------------------------------------------------
    // reset or instantiate all the objects
    //---------------------------------------------------------------------------------------------------------------
    public void resetObjects() {
        // OBJECTS -----------
        arena = new GameArena(800, 500);
        collisionTracker = new CollisionTracker();
        movementManager = new MovementManager();
        scoreBoard = new ScoreBoard("0 : 0", 40, 355, 60, "BLACK1");
        // KLASK ARENA -------
        klaskArena = new KlaskArena(50, 100, 700, 350, "BLUE1", -4);
        hole1 = new Ball(700, 280, 60, "BLACK1");
        hole2 = new Ball(100, 280, 60, "BLACK1");
        // PLAYERS -----------
        p1 = new Player(600, 280, "BLACK2", 1);
        p2 = new Player(200, 280, "BLACK2", 2);
        // GAME BALL ---------
        Random rand = new Random();
        gball = new GameBall(100 + rand.nextInt(2)*600, 150 + rand.nextInt(2)*250, 30, "YELLOW1");
        // MAGNETS -----------
        for (int i = 0; i < 3; i++) {
            magnets[i] = new Magnet(400, 175+i*100, 20, "WHITE1");
        }
    }

    //---------------------------------------------------------------------------------------------------------------
    // freeze game for the given time before next round
    //---------------------------------------------------------------------------------------------------------------
    public static void freezeGame() {
        frozen = true;
        frozenCount = freezeTime;
    }

}
