package base;

import java.awt.Color;

public class Main {

    public static GameArena arena;
    public static KlaskArena klaskArena;
    public static CollisionTracker collisionTracker;
    public static MovementManager movementManager;
    public static double moveSpeed = 0.00006;
    public static double deltaTime = 1;
    public static Player p1;
    public static Player p2;
    public static GameBall gball;
    public static Magnet[] magnets = new Magnet[3];
    public static ScoreBoard scoreBoard;
    public static Ball hole1;
    public static Ball hole2;

    public static final int freezeTime = 100000;
    public static boolean frozen = false;
    public static double frozenCount = 0;
    public static int whoWon = 0;

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

    public static void wonRound(int who) {
        scoreBoard.wonPoint(who);
        resetRound();
    }

    public static void resetRound() {
        p1.removeFromArena();
        p2.removeFromArena();
        arena.removeBall(gball);
        p1 = new Player(500, 200, "BLACK2", 1);
        p2 = new Player(200, 200, "BLACK2", 2);
        gball = new GameBall(250, 250, 30, "YELLOW1");
        for (int i = 0; i < 3; i++) {
            arena.removeBall(magnets[i]);
            magnets[i] = new Magnet(400, 175+i*100, 20, "WHITE1");
        }
    }
    
    public void resetObjects() {
        arena = new GameArena(800, 500);
        klaskArena = new KlaskArena(50, 100, 700, 350, "BLUE1", -4);
        hole1 = new Ball(700, 280, 60, "BLACK1");
        hole2 = new Ball(100, 280, 60, "BLACK1");
        collisionTracker = new CollisionTracker();
        movementManager = new MovementManager();
        scoreBoard = new ScoreBoard("0 : 0", 40, 355, 60, "BLACK1");
        p1 = new Player(500, 200, "BLACK2", 1);
        p2 = new Player(200, 200, "BLACK2", 2);
        gball = new GameBall(250, 250, 30, "YELLOW1");
        for (int i = 0; i < 3; i++) {
            magnets[i] = new Magnet(400, 175+i*100, 20, "WHITE1");
        }
    }

    public static void freezeGame() {
        frozen = true;
        frozenCount = freezeTime;
    }

}
