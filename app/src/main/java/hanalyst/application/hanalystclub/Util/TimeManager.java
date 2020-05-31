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
}
