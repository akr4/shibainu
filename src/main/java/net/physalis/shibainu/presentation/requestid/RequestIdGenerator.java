package net.physalis.shibainu.presentation.requestid;

import java.util.concurrent.ThreadLocalRandom;

public final class RequestIdGenerator {

    private static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public static String generate() {
        return Radix32.encode(RANDOM.nextInt(Radix32.radix * 30 * 30 * 30), 4);
    }

    private RequestIdGenerator() {
    }

}
