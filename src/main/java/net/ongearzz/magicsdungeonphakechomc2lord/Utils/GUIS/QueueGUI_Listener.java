package net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

public class QueueGUI_Listener implements Listener {

    public static Map<Player, Queue> queues = new HashMap<>();

    @EventHandler
    public void onclick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals("Tìm Party!")) {
            if (event.getCurrentItem().getType() != Material.AIR) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a")) {
                    event.setCancelled(true);
                }
                String leader = event.getCurrentItem().getItemMeta().getDisplayName();
                PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(Bukkit.getPlayer(leader));
            }
        }
    }
}
