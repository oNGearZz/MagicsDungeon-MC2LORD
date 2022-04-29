package net.ongearzz.magicsdungeonphakechomc2lord.Utils;

import java.util.concurrent.ThreadLocalRandom;

public class Random {
    public static boolean chance(int chance) {
        return ThreadLocalRandom.current().nextInt(1001) <= chance;
    }
}
