package hanalyst.application.hanalystclub.Util;

import android.content.Context;

import java.util.Date;

public class TimeManager {
    private static final long NINETY_MINUTES = 5400000;
    private Context context;

    public TimeManager(Context context) {
        this.context = context;
    }

    public String getEndTime() {
        Date currentDate = new Date();
        long approximateEndTime = currentDate.getTime() + NINETY_MINUTES;
        return String.valueOf(approximateEndTime);
    }

    public String getCurrentTime() {
        return String.valueOf(new Date().getTime());
    }

    public boolean isGameInProgress(String endTime) {
        Date currentDate = new Date();
        return currentDate.getTime() <= Long.parseLong(endTime);
    }

    public boolean isGameOverhead(String endTime) {
        Date currentDate = new Date();
        return currentDate.getTime() > Long.parseLong(endTime);
    }

    public String getMinutesPlayed(String startTime) {
        Date currentDate = new Date();
        return String.valueOf(new Date(currentDate.getTime() - Long.parseLong(startTime)).getMinutes());
    }
}
