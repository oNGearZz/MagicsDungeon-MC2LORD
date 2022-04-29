package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemData;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.LootChestConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SetupGui {
    public static void customitem(Player player) {
        int Item;
        Inventory inventory = Bukkit.createInventory(null, 54, "LootChest System");
        FileConfiguration configuration = LootChestConfig.getConfiguration();
        if (configuration.contains("Items")) {
            Item = configuration.contains("Items") ? configuration.getConfigurationSection("Items").getKeys(false).size() : 0;
        } else {
            Item = 0;
        }
        {
            for (int i = 45; i < 53; i++) {
                inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE)
                        .setDisplayName("§b")
                        .build());
            }
            inventory.setItem(53, new ItemBuilder(Material.IRON_SWORD)
                    .setDisplayName("§aAdd new Item! #" + Item)
                    .addLore("")
                    .addLore("§e§oClick To Add")
                    .build());
            inventory.setItem(45, new ItemBuilder(Material.ARROW)
                    .setDisplayName("BACK")
                    .build());
            inventory.setItem(49, new ItemBuilder(Material.PAPER)
                    .setDisplayName("info")
                    .addLore("§a Đã Add Đc Chance vào Item :))")
                    .build());
            int slot = 0;
            for (int i = 1; i < (Item); i++) {
                ItemStack stack = ItemData.GetItemStack(configuration.getString("Items." + i + ".data"));
                ItemMeta meta = stack.getItemMeta();
                List<String> lore = meta.getLore();
                inventory.setItem(slot, new ItemBuilder(stack.getType())
                        .setDisplayName(meta.getDisplayName())
                        .setAmount(stack.getAmount())
                        .setLore(lore)
                        .addLore("§aChance: §6" + configuration.getString("Items." + i + ".chance"))
                        .build());
                if (slot <= 44) {
                    slot++;
                }
            }
        }
        player.openInventory(inventory);
    }

    public static void addnewitem(Player player, String num) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Add New Item: " + num);
        {
            for (int i = 0; i < 27; i++) {
                inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE)
                        .setDisplayName("§b")
                        .build());
            }
            inventory.setItem(13, new ItemBuilder(Material.AIR).build());
            player.openInventory(inventory);
        }
    }

}

