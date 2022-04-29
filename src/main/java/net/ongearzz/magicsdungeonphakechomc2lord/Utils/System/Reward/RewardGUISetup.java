package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Reward;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemData;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.MapsConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener.PlayerChatEvent;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupGui;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RewardGUISetup implements Listener {

    public static void open(Player player, String score) {
        Inventory inventory = Bukkit.createInventory(null, 54, "Reward System: " + score);
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder(), "Reward.yml");
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        int Item = configuration.contains(score + ".Items") ? configuration.getConfigurationSection(score+ ".Items").getKeys(false).size() : 0;
        for (int i = 45; i < 53; i++) {
            ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 1);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName("§b");
            stack.setItemMeta(meta);
            inventory.setItem(i, stack);
        }
        inventory.setItem(53, new ItemBuilder(Material.IRON_SWORD)
                .setDisplayName("§aAdd new Item! #" + Item)
                .addLore("")
                .addLore("§e§oClick To Add")
                .build());
        inventory.setItem(49, new ItemBuilder(Material.PAPER)
                .setDisplayName("info")
                .addLore("§a Đã Add Đc Chance vào Item :))")
                .build());
        int slot = 0;
        for (int i = 1; i < (Item); i++) {
            if (configuration.getString(score + ".Items." + i + ".data") != null) {
                ItemStack stack = ItemData.GetItemStack(configuration.getString(score + ".Items." + i + ".data"));
                ItemMeta meta = stack.getItemMeta();
                List<String> lore = meta.getLore();
                inventory.setItem(slot, new ItemBuilder(stack.getType())
                        .setDisplayName(meta.getDisplayName())
                        .setAmount(stack.getAmount())
                        .setLore(lore)
                        .addLore("§aChance: §6" + configuration.getString(score + ".Items." + i + ".chance"))
                        .build());
                if (slot <= 44) {
                    slot++;
                }
            }
        }
        player.openInventory(inventory);
    }

    public static void addnewitem(Player player, String num,String score) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Add New Item To Reward!: " + num);
        {
            for (int i = 0; i < 27; i++) {
                inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE)
                        .setDisplayName("§b")
                        .build());
            }
            ItemStack stack = new ItemStack(Material.STAINED_GLASS_PANE).clone();
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(score);
            stack.setItemMeta(meta);
            inventory.setItem(0,stack);
            inventory.setItem(13, new ItemBuilder(Material.AIR)
                    .build());
            player.openInventory(inventory);
        }
    }

    @EventHandler
    public void onclose(InventoryCloseEvent event) {
        try {
            String score = null;
            Player player = (Player) event.getPlayer();
            if (event.getInventory().getItem(0).getItemMeta().getDisplayName() != null) {
                score = event.getInventory().getItem(0).getItemMeta().getDisplayName();
            }
            for (int i = 0; i < 12; i++) {
                event.getInventory().setItem(i, new ItemBuilder(Material.AIR).build());
            }
            for (int i = 14; i < 27; i++) {
                event.getInventory().setItem(i, new ItemBuilder(Material.AIR).build());
            }
            if (!event.getInventory().getItem(13).getType().equals(Material.AIR)) {
                String data = ItemData.itemStackArrayToBase64(event.getInventory().getContents());
                String num = event.getView().getTitle().split("Add New Item To Reward!: ")[1];
                File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder(), "Reward.yml");
                YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                try {
                    configuration.set(score + ".Items." + num + ".data", data);
                    configuration.set(score + ".Items." + num + ".chance", 0.0);
                    configuration.save(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MagicsDungeonPhakeChoMC2LORD.getInstance().rewardConfig.save();
                PlayerChatEvent.playerEdit2.put(player, "reward-chance-" + num + "-" + score);
                player.sendMessage("§e--------------------");
                player.sendMessage("§c(Require!) §aSet Chance! (123 = 12.3%); or (489 = 48.9%)");
                player.sendMessage("§aCancel Để Thoát)");
                player.sendMessage("§e--------------------");
            } else {
                player.sendMessage("§cCancel!");
                open(player, score);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onclick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        try {
            if (event.getView().getTitle().contains("Reward System: ")) {
                event.setCancelled(true);
                String score = event.getView().getTitle().split("Reward System: ")[1];
                if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§aAdd new Item! #")) {
                    player.closeInventory();
                    String num = event.getCurrentItem().getItemMeta().getDisplayName().split("§aAdd new Item! #")[1];
                    addnewitem(player,num, score);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage("Error!");
        }
    }
}

