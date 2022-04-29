package net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.text.FieldPosition;
import java.util.ArrayList;
import java.util.List;

public class PartyFile {
    private static File file;
    public static List<String> PartyInviteHelp = new ArrayList<>();
    public static List<String> PartyCreateThanhCong = new ArrayList<>();
    public static List<String> PartyCreateKoThanhCong = new ArrayList<>();
    public static List<String> PartyDisbandThanhCong = new ArrayList<>();

    public static void load() {
        file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Partys", "PartyList.yml");
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        save();
    }

    public static YamlConfiguration getConfig() {
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        return yaml;
    }


    public static void save() {
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
