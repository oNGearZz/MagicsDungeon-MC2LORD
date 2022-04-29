package net.ongearzz.magicsdungeonphakechomc2lord.Utils;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.apache.commons.io.FileUtils;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LootChestConfig {
    private static File file;
    private static YamlConfiguration configuration;

    public static void load() {
        file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder(), "/LootChest.yml");
        if (!file.exists()) {
            try {
                Files.createFile(Paths.get(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/LootChest.yml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        configuration = new YamlConfiguration();
        try {
            configuration.load(file);
        } catch (IOException | InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
        if (!getConfiguration().contains("Items")) {
            getConfiguration().set("Items.0.test", "test");
            save();
        }

        save();

    }

    public static void save() {
        try {
            configuration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static File getFile() {
        return file;
    }

    public static YamlConfiguration getConfiguration() {
        return configuration;
    }
}

