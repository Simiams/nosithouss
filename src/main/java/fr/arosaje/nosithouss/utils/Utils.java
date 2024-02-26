package fr.arosaje.nosithouss.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;

public class Utils {

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp addTimestamp(Timestamp currentTimestamp, int secondsToAdd) {
        return new Timestamp(currentTimestamp.getTime() + (secondsToAdd * 1000L));
    }

    public static String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
