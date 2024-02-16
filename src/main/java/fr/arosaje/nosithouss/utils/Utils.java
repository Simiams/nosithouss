package fr.arosaje.nosithouss.utils;

import java.sql.Timestamp;

public class Utils {

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp addTimestamp(Timestamp currentTimestamp, int secondsToAdd) {
        return new Timestamp(currentTimestamp.getTime() + (secondsToAdd * 1000L));
    }
}
