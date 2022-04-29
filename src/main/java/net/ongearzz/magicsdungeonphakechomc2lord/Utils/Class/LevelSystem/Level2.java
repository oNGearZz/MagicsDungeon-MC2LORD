package net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Level2 {

    //    public static String getPlayerClass(Player player, String did) {
//        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Dungeons/" + did, "Dungeon.yml");
//        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
//        return yaml.getString("Class." + player.getName() + ".class");
//    }
    public static String getClass(Player player, DungeonManager manager) {
        FileConfiguration configuration = manager.getConfig();
        String clazz = configuration.getString("Class." + player.getName() + ".class");
        return clazz;
    }

//    public static int getExp(Player player, String clazz, String did) {
//        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Dungeons/" + did, "Dungeon.yml");
//        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
//        return yaml.getInt("Level." + clazz);
//    }
//
//    public static int getLvl(Player player, String clazz,String did) {
//        int exp = getExp(player, clazz, did);
//        int lvl = 0;
//        for (String level : LevelConfig.getConfig().getConfigurationSection("Levels").getKeys(false)) {
//            if (exp >= LevelConfig.getConfig().getInt("Levels." + level) && lvl < Integer.parseInt(level))
//                lvl = Integer.parseInt(level);
//        }
//        return lvl;
//    }
//
//    public static int expToNextLvl(Player player, String clazz, String did) {
//        int level = getLvl(player, clazz,did);
//        int nextlevel = LevelConfig.getConfig().getInt("Levels." + (level + 1));
//        if (nextlevel == 0)
//            return 0;
//        return nextlevel - LevelConfig.getConfig().getInt("Levels." + level);
//    }
//
//    public static int bnexpr(Player player, String clazz,String did) {
//        int xp = getExp(player, clazz,did);
//        xp -= LevelConfig.getConfig().getInt("Levels." + getLvl(player, clazz,did));
//        return xp;
//    }
}
