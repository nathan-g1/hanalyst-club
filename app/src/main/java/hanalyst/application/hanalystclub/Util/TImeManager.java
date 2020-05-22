package hanalyst.application.hanalystclub.Util;

import java.util.Date;

public class TImeManager {
    private static final long NINETY_MINUTES = 5400000;

    public String getEndTime() {
        Date currentDate = new Date();
        long approximateEndTime = currentDate.getTime() + NINETY_MINUTES;
        return String.valueOf(approximateEndTime);
    }

    public String getCurrentTime() {
        return String.valueOf(new Date().getTime());
    }
}
