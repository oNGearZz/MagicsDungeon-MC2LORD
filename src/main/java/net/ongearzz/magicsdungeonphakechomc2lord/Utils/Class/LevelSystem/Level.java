package net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Storage.HashMapStorge;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Level {
    public static String getPlayerClass(Player player) {
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Players", player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        return yaml.getString("Class");
    }

    public static int getExp(Player player, String clazz) {
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Players", player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        return yaml.getInt("Level." + clazz);
    }

    public static int getLvl(Player player, String clazz) {
        int exp = getExp(player, clazz);
        int lvl = 0;
        for (String level : LevelConfig.getConfig().getConfigurationSection("Levels").getKeys(false)) {
            if (exp >= LevelConfig.getConfig().getInt("Levels." + level) && lvl < Integer.parseInt(level))
                lvl = Integer.parseInt(level);
        }
        return lvl;
    }

    public static int expToNextLvl(Player player, String clazz) {
        int level = getLvl(player, clazz);
        int nextlevel = LevelConfig.getConfig().getInt("Levels." + (level + 1));
        if (nextlevel == 0)
            return 0;
        return nextlevel - LevelConfig.getConfig().getInt("Levels." + level);
    }

    public static int bnexpr(Player player, String clazz) {
        int xp = getExp(player, clazz);
        xp -= LevelConfig.getConfig().getInt("Levels." + getLvl(player, clazz));
        return xp;
    }

    public static void setClass(Player player, String clazz) {
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Players", player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        try {
            yaml.set("Class", clazz);
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
