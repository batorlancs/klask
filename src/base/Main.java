package base;

public class Main {

    public static GameArena arena;
    public static CollisionTracker collisionTracker;
    public static double moveSpeed = 0.0006;
    public static Player p1;
    public static Player p2;
    public static GameBall gball;
    public static Magnet[] magnets = new Magnet[3];


    public static void main(String[] args) {
        Main main = new Main();
        main.resetObjects();

        while (true) {
            System.out.println("breh");
            collisionTracker.checkCollisions();
            //set direction for players, balls, and magnets
            main.setMoves();
            main.makeMoves();
            //move them with their current speed
        }
    }
    
    public void resetObjects() {
        arena = new GameArena(800, 500);
        collisionTracker = new CollisionTracker();
        p1 = new Player(100, 100, "ORANGE");
        p2 = new Player(500, 100, "BLUE");
        gball = new GameBall(250, 250, 30, "WHITE");
        for (int i = 0; i < 3; i++) {
            magnets[i] = new Magnet(100, 100+i*100, 15, "GRAY");
        }
    }
    
    public void setMoves() {
        // player 1
        if (arena.upPressed()) p1.setDy(-1);
        if (arena.downPressed()) p1.setDy(1);
        if (!arena.upPressed() && !arena.downPressed()) p1.setDy(0);
        if (arena.leftPressed()) p1.setDx(-1);
        if (arena.rightPressed()) p1.setDx(1);
        if (!arena.leftPressed() && !arena.rightPressed()) p1.setDx(0);
        // player 2
        if (arena.wPressed()) p2.setDy(-1);
        if (arena.sPressed()) p2.setDy(1);
        if (!arena.wPressed() && !arena.sPressed()) p2.setDy(0);
        if (arena.aPressed()) p2.setDx(-1);
        if (arena.dPressed()) p2.setDx(1);
        if (!arena.aPressed() && !arena.dPressed()) p2.setDx(0);
        // ball
        // if there is a collision with player --> gets their speed and moves
    }

    public void makeMoves() {
        gball.move();
        p1.move();
        p2.move();
    }

}
