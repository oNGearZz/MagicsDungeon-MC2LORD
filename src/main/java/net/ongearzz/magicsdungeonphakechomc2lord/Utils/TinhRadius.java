package net.ongearzz.magicsdungeonphakechomc2lord.Utils;


import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class TinhRadius {
    public List<Location> blocks = new ArrayList<>();
    public List<String> blocks2 = new ArrayList<>();
    public List<Location> customRadiusBlocks = new ArrayList<>();

    public void Radius(org.bukkit.Location location) {
        Block start = location.getBlock();
        for (double x = start.getLocation().getX() - 1; x <= start.getLocation().getX() + 1; x++) {
            for (double y = start.getLocation().getY() - 1; y <= start.getLocation().getY() + 1; y++) {
                for (double z = start.getLocation().getZ() - 1; z <= start.getLocation().getZ() + 1; z++) {
                    Location loc = new Location(start.getWorld(), x, y, z);
                    blocks.add(loc);
                    String world = loc.getWorld().getName();
                    int xx = loc.getBlockX();
                    int yy = loc.getBlockY();
                    int zz = loc.getBlockZ();
                    blocks2.add(world + " " + xx + " " + yy + " " + zz);
                }
            }
        }
    }

    public void customRadius(Location location, int radius) {
        Block start = location.getBlock();
        for (double x = start.getLocation().getX() - radius; x <= start.getLocation().getX() + radius; x++) {
            for (double y = start.getLocation().getY() - radius; y <= start.getLocation().getY() + radius; y++) {
                for (double z = start.getLocation().getZ() - radius; z <= start.getLocation().getZ() + radius; z++) {
                    Location loc = new Location(start.getWorld(), x, y, z);
                    customRadiusBlocks.add(loc);
                }
            }
        }
    }
}
