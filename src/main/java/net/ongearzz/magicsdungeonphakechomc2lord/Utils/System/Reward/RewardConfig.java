package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Reward;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class RewardConfig {
    public void load() {
     File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder(), "Reward.yml");
     YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
     if (!file.exists()) {
         save();
     }
     try {
         configuration.set("S.Items.0.test","null");
         configuration.set("A.Items.0.test","null");
         configuration.set("B.Items.0.test","null");
         configuration.set("C.Items.0.test","null");
         configuration.set("D.Items.0.test","null");
         configuration.save(file);
     } catch (IOException e) {
         e.printStackTrace();
     }
    }

    public YamlConfiguration getConfiguration() {
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder(), "Reward.yml");
        YamlConfiguration configurationn = YamlConfiguration.loadConfiguration(file);
        return configurationn;
    }

    public void save() {
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder(), "Reward.yml");
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        try {
            yamlConfiguration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
