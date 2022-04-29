package net.ongearzz.magicsdungeonphakechomc2lord.Utils;

import com.google.common.collect.Maps;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Checker {
    public static int tick = 0;
    public static int times = 0;

    public static void start() {
        check();
        timesChecker();
        asyncRun();
    }

    public static void check() {
        new BukkitRunnable() {
            @Override
            public void run() {
                tick++;
                MapsConfig.spawmParticle();
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 5L);
    }

    public static void timesChecker() {
        new BukkitRunnable() {
            @Override
            public void run() {
                times++;
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 2L, 2L);
    }

    public static void asyncRun() {
        new BukkitRunnable() {
            @Override
            public void run() {
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 0);
    }
}
