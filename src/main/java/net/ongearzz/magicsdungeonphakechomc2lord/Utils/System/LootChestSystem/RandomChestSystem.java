package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem;

import net.ongearzz.magicsdungeonphakechomc2lord.Utils.LootChestConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.TinhPhanTram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class RandomChestSystem {
    public static void fill(Inventory inventory) {
        inventory.clear();
        YamlConfiguration configuration = LootChestConfig.getConfiguration();
        int Item = configuration.contains("Items") ? configuration.getConfigurationSection("Items").getKeys(false).size() : 0;
        for (int slotIndex = 0; slotIndex < inventory.getSize(); slotIndex++) {
            for (int i = 0; i < Item; i++) {
                if (configuration.getString("Items." + i + ".data") != null) {
                    ItemStack stack = LootItem.getFormLccf(String.valueOf(i));
                    if (ThreadLocalRandom.current().nextInt(1001) <= configuration.getInt("Items." + i + ".chance")) {
                        inventory.setItem(slotIndex, stack);
                    }
                }
            }
        }
    }
}
