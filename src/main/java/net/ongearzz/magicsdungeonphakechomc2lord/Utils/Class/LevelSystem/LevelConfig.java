package net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelConfig {
    private static File file;

    public static List<String> SatThuLore = new ArrayList<>();
    public static List<String> DoDonLore = new ArrayList<>();
    public static List<String> PhapSuLore = new ArrayList<>();
    public static List<String> XaThuLore = new ArrayList<>();
    public static List<String> HoTroLore = new ArrayList<>();


    public static void load() {
        File fil1 = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/LevelConfig", "LvlConfig.yml");
        if (!fil1.exists()) {
            file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/LevelConfig", "LvlConfig.yml");
            YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
            save();
            try {
                yaml.set("Levels.1", 0);
                yaml.set("Levels.2", 10);
                yaml.set("Levels.3", 50);
                yaml.set("Levels.4", 100);
                yaml.set("Levels.5", 200);
                yaml.set("Levels.6", 400);
                yaml.set("Levels.7", 1000);
                yaml.set("Levels.8", 1500);
                yaml.set("Levels.9", 2000);
                yaml.set("Levels.10", 2500);

                yaml.set("Levels.11", 3000);
                yaml.set("Levels.12", 4000);
                yaml.set("Levels.13", 5000);
                yaml.set("Levels.14", 6000);
                yaml.set("Levels.15", 7000);
                yaml.set("Levels.16", 9000);
                yaml.set("Levels.17", 11000);
                yaml.set("Levels.18", 13000);
                yaml.set("Levels.19", 15000);
                yaml.set("Levels.20", 20000);

                yaml.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static YamlConfiguration getConfig() {
        file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/LevelConfig", "LvlConfig.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        return yaml;
    }

    public static void save() {
        file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/LevelConfig", "LvlConfig.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
