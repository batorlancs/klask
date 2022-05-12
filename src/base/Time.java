package base;

public class Time {

    // *
    // OBJECT TO CALCULATE DELTA TIME
    // *

    public static double timeStarted = System.nanoTime();
    public static double getTime() {
        return System.nanoTime() - timeStarted;
    }
}
