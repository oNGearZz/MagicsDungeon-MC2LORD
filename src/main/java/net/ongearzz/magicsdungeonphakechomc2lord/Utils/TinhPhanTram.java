package net.ongearzz.magicsdungeonphakechomc2lord.Utils;

import java.util.concurrent.ThreadLocalRandom;

public class TinhPhanTram {

    public boolean tinhPhanTram(int tile) {
        return ThreadLocalRandom.current().nextInt(1001) <= tile;
    }
}
