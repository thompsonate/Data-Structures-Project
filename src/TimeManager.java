import java.util.Date;

public class TimeManager {
    public long startTime;

    public void startTimer() {
        startTime = System.currentTimeMillis();
    }

    public long endTimer() {
        long now = System.currentTimeMillis();

        return now - startTime;
    }
}
