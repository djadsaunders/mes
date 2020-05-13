package com.djad.mestestdata;

import java.util.concurrent.ThreadLocalRandom;

public abstract class MESUtil {
    public static int generateRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, ++max);
    }
}
