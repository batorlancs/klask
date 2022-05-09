package base;

public class Time {



    public static double timeStarted = System.nanoTime();
    public static double getTime() {
        return System.nanoTime() - timeStarted;
    }
}
