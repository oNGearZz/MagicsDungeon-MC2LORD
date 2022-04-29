package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.animation;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class armorstandAnimation {
    public static void start(ArmorStand stand, Location location, int i) {
        new BukkitRunnable() {
            double t = 0;

            @Override
            public void run() {
                t = t + 0.05 * Math.PI;
                for (double theta = 0; theta <= 5 * Math.PI; theta = theta + Math.PI / i) { //2
                    double x = t * Math.cos(theta);
                    double y = 15 * Math.exp(-0.5 * t) * Math.sin(t) + 1;
                    double z = t * Math.sin(theta);
                    location.add(x, y, z);
                    stand.teleport(location);
                    location.subtract(x, y, z);
                }
                if (t > 3.4) {
                    this.cancel();
                }
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 1);
    }

    public static void defaultStart(boolean UsePi, ArmorStand stand, Location location, double i,int i1,Player player, ArmorStand origin) {
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        new BukkitRunnable() {
            int tick = 0;
            @Override
            public void run() {
                if (info2.isIg()) {
                    if (info2.getManager() != null) {
                        DungeonManager manager = info2.getManager();
                        if (tick <= 30) {
                            if (info2.isB()) {
                                EulerAngle eulerAngle = origin.getHeadPose().add(0, 0, 0.2);
                                origin.setHeadPose(eulerAngle);
                                info2.setB(false);
                            } else {
                                EulerAngle eulerAngle = origin.getHeadPose().add(0, 0, -0.2);
                                origin.setHeadPose(eulerAngle);
                                info2.setB(true);
                            }
                        } else {
                            if (UsePi) {
                                start(stand,location, i1);
                            } else {
                                start2(stand,location,i);
                            }
                            this.cancel();
                        }
                    }
                }
                tick++;
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(),0,2);
    }

    public static void start2(ArmorStand stand, Location location, double i) {
        new BukkitRunnable() {
            double t = 0;

            @Override
            public void run() {
                t = t + 0.05 * Math.PI;
                for (double theta = 0; theta <= 5 * Math.PI; theta = theta + i) { //6.7 // 5.85
                    double x = t * Math.cos(theta);
                    double y = 15 * Math.exp(-0.5 * t) * Math.sin(t) + 1;
                    double z = t * Math.sin(theta);
                    location.add(x, y, z);
                    stand.teleport(location);
                    location.subtract(x, y, z);
                }
                if (t > 3.4) {
                    this.cancel();
                }
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 1);
    }
    public static void test(Player player, double i, Location location) {
        new BukkitRunnable() {
            double t = 0;
            @Override
            public void run() {
                t = t + 0.05 * Math.PI;
                for (double theta = 0; theta <= 5 * Math.PI; theta = theta + i) { //6.7 // 5.85
                    double x = t * Math.cos(theta);
                    double y = 15 * Math.exp(-0.5 * t) * Math.sin(t) + 1;
                    double z = t * Math.sin(theta);
                    location.add(x, y, z);
                    player.teleport(location);
                    location.subtract(x, y, z);
                }
                if (t > 4) {
                    this.cancel();
                }
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 1);
    }
}
