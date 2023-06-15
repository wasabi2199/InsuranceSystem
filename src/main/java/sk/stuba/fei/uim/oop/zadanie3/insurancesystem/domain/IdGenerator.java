package sk.stuba.fei.uim.oop.zadanie3.insurancesystem.domain;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final AtomicLong generator = new AtomicLong(1);

    public static long newId() {
        return generator.getAndIncrement();
    }
}
