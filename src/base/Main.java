package base;

public class Main {

    public static GameArena arena;
    public static CollisionTracker collisionTracker;
    public static MovementManager movementManager;
    public static double moveSpeed = 0.00006;
    public static double deltaTime = 1;
    public static Player p1;
    public static Player p2;
    public static GameBall gball;
    public static Magnet[] magnets = new Magnet[3];


    public static void main(String[] args) {
        Main main = new Main();
        main.resetObjects();

        double beginTime = Time.getTime();
        double endTime;

        while (true) {
            System.out.println(deltaTime);
            collisionTracker.checkCollisions();
            //set direction for players, balls, and magnets
            movementManager.setMoves();
            movementManager.makeMoves();
            //move them with their current speed

            endTime = Time.getTime();
            deltaTime = (endTime - beginTime) / 100;
            beginTime = endTime;
        }
    }
    
    public void resetObjects() {
        arena = new GameArena(800, 500);
        collisionTracker = new CollisionTracker();
        movementManager = new MovementManager();
        p1 = new Player(100, 100, "ORANGE");
        p2 = new Player(500, 100, "BLUE");
        gball = new GameBall(250, 250, 30, "WHITE");
        for (int i = 0; i < 3; i++) {
            magnets[i] = new Magnet(100, 100+i*100, 15, "GRAY");
        }
    }

}
