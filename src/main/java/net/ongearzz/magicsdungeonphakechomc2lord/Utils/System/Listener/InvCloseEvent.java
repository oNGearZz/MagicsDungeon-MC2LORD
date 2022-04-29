package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemData;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.LootChestConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupGui;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InvCloseEvent implements Listener {
    @EventHandler
    public void onclose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().contains("Add New Item: ")) {
            if (event.getInventory().getItem(2).getItemMeta().getDisplayName().equals("§b")) {
                if (event.getInventory().getItem(8).getItemMeta().getDisplayName().equals("§b")) {
                    if (event.getInventory().getItem(12).getItemMeta().getDisplayName().equals("§b")) {
                        for (int i = 0; i < 12; i++) {
                            event.getInventory().setItem(i, new ItemBuilder(Material.AIR).build());
                        }
                        for (int i = 14; i < 27; i++) {
                            event.getInventory().setItem(i, new ItemBuilder(Material.AIR).build());
                        }
                        if (!event.getInventory().getItem(13).getType().equals(Material.AIR)) {
                            String data = ItemData.itemStackArrayToBase64(event.getInventory().getContents());
                            String num = event.getView().getTitle().split("Add New Item: ")[1];
                            LootChestConfig.getConfiguration().set("Items." + num + ".data", data);
                            LootChestConfig.getConfiguration().set("Items." + num + ".chance", 0.0);
                            LootChestConfig.save();
                            PlayerChatEvent.playerEdit.put(player, "LootChest-chance-" + num);
                            player.sendMessage("§e--------------------");
                            player.sendMessage("§c(Require!) §aSet Chance! (123 = 12.3%); or (489 = 48.9%)");
                            player.sendMessage("§aCancel Để Thoát)");
                            player.sendMessage("§e--------------------");
                        } else {
                            player.sendMessage("§cCancel!");
                            SetupGui.customitem(player);
                        }
                    }
                }
            }
        }
    }
}
