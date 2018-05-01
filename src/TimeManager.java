import java.util.Date;

public class TimeManager {
    public long startTime;

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public long endTimer() {
        long now = System.nanoTime();

        return now - startTime;
    }
}
