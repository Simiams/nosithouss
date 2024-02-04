package fr.arosaje.nosithouss.utils;

import java.sql.Timestamp;

public class Utils {

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
}
