package net.ongearzz.magicsdungeonphakechomc2lord.Map1;

import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;


public class Manager {

    public static Location spawmLoc(World world) {
        return new Location(world, -186, 71, -153, -90.0F, 0);
    }

    public static Location wallLoc(World world) {
        return new Location(world, -169.0D, 70.0D, -153.0D);
    }

    public static Location npcLoc(World world) {
        return new Location(world, -173.0D, 69.0D, -153.0D);
    }

    public static Location getbossloc(World world) {
        return new Location(world, -9D, 70D, 1D);
    }

    public static Location rewardLoc(World world) {
        return new Location(world, -9, 69, 66);
    }

    //        loc.add("-103 69 -24");
//        loc.add("-103 69 -25");
//        loc.add("-103 69 -26");
//
//        loc.add("-103 70 -24");
//        loc.add("-103 70 -25");
//        loc.add("-103 70 -26");
//
//        loc.add("-103 71 -24");
//        loc.add("-103 71 -25");
//        loc.add("-103 71 -26");
//
//        loc.add("-103 72 -24");
//        loc.add("-103 72 -25");
//        loc.add("-103 72 -26");
    public static List<String> getLoc2(World world) {
        List<String> loc = new ArrayList<>();
        if (loc.size() == 0) {
            loc.add("-103 69 -24");
            loc.add("-103 69 -25");
            loc.add("-103 69 -26");

            loc.add("-103 70 -24");
            loc.add("-103 70 -25");
            loc.add("-103 70 -26");

            loc.add("-103 71 -24");
            loc.add("-103 71 -25");
            loc.add("-103 71 -26");

            loc.add("-103 72 -24");
            loc.add("-103 72 -25");
            loc.add("-103 72 -26");
        } else {
            loc.clear();
            loc.add("-103 69 -24");
            loc.add("-103 69 -25");
            loc.add("-103 69 -26");

            loc.add("-103 70 -24");
            loc.add("-103 70 -25");
            loc.add("-103 70 -26");

            loc.add("-103 71 -24");
            loc.add("-103 71 -25");
            loc.add("-103 71 -26");

            loc.add("-103 72 -24");
            loc.add("-103 72 -25");
            loc.add("-103 72 -26");
        }
        return loc;
    }
}
