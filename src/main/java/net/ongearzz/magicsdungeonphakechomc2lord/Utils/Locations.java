package net.ongearzz.magicsdungeonphakechomc2lord.Utils;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Locations {
    private static final Map<String, Location> locations = new HashMap<>();

    public static void add(Location loc, String location) {
        locations.put(location, loc);
    }

    public static void remove(String loc) {
        locations.remove(loc);
    }

    public static Location get(String loc) {
        Location location = locations.get(loc);
        return location;
    }

    public static Map<String, Location> getLocations() {
        return locations;
    }

    public static void loadMaps(String world) {
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Maps", world + ".yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        try {
            List<String> mobs = configuration.getStringList("Mobs");
            for (int i = 0; i < mobs.size(); i++) {
                String loc = mobs.get(i).split(";")[2];
                String w = loc.split(" ")[0];
                int x = Integer.parseInt(loc.split(" ")[1]);
                int y = Integer.parseInt(loc.split(" ")[2]);
                int z = Integer.parseInt(loc.split(" ")[3]);
                add(new Location(Bukkit.getWorld(w), x, y, z), w + " " + x + " " + y + " " + z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
