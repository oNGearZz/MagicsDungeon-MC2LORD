package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupGui;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupInv;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InvClickEvent implements Listener {
    @EventHandler
    public void onclick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();
        if (title.equals("LootChest System")) {
            if (event.getInventory().getItem(49).getItemMeta().getDisplayName().equals("info")) {
                if (event.getInventory().getItem(53).getItemMeta().getDisplayName().contains("§aAdd new Item! #")) {
                    event.setCancelled(true);
                    if (event.getCurrentItem().getItemMeta().getDisplayName().contains("§aAdd new Item! #")) {
                        String num = event.getCurrentItem().getItemMeta().getDisplayName().split("§aAdd new Item! #")[1];
                        SetupGui.addnewitem(player, num);
                    }
                }
            }
        } else if (title.contains("Add New Item: ")) {
            if (event.getInventory().getItem(2).getItemMeta().getDisplayName().equals("§b")) {
                if (event.getInventory().getItem(8).getItemMeta().getDisplayName().equals("§b")) {
                    if (event.getInventory().getItem(12).getItemMeta().getDisplayName().equals("§b")) {
                        if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b")) {
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onclick2(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        if (event.getView().getTitle().equals("§6Reward Chest")) {
            event.setCancelled(true);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§b")) {
                event.setCancelled(true);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a")) {
                event.setCancelled(true);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aMở")) {
                info2.getManager().openLootChest(player, event.getInventory());
                player.closeInventory();
                event.setCancelled(true);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().contains("'s lootChest!")) {
                event.setCancelled(true);
            }
        }
    }
}
