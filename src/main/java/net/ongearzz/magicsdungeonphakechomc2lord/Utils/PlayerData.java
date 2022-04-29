package net.ongearzz.magicsdungeonphakechomc2lord.Utils;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Shulker;
import org.bukkit.inventory.ItemStack;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerData {
    private File dataF;
    private FileConfiguration cf;
    private Player player;
    private YamlConfiguration yaml;
    private String PlayerUUID;

    public PlayerData(Player player) {
        this.player = player;
    }

    public PlayerData(String uuid) {
        this.PlayerUUID = uuid;
    }

    public YamlConfiguration getPlayerDataConfig() {
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Players", player.getUniqueId() + ".yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        return yaml;
    }

    public void load() {
        dataF = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Players", player.getUniqueId() + ".yml");
        yaml = YamlConfiguration.loadConfiguration(dataF);
        cf = YamlConfiguration.loadConfiguration(dataF);
        try {
            if (dataF.createNewFile()) {
                yaml.set("Class", "NONE");
                yaml.set("Level.SatThu", 0);
                yaml.set("Level.DoDon", 0);
                yaml.set("Level.PhapSu", 0);
                yaml.set("Level.XaThu", 0);
                yaml.set("Level.HoTro", 0);
                yaml.set("Wins", 0);
                yaml.set("TranDaChoi", 0);
                yaml.save(dataF);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveFile();
    }

    public File getFile() {
        return dataF;
    }

    public YamlConfiguration getYamlConfig() {
        return yaml;
    }

    public FileConfiguration getConfig() {
        cf = YamlConfiguration.loadConfiguration(dataF);
        return cf;
    }

    public void saveFile() {
        yaml = YamlConfiguration.loadConfiguration(dataF);
        try {
            yaml.save(dataF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
