package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.MapsConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupInv;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BlockPlaceEvent implements Listener {
    public static Map<UUID, String> edit2 = new HashMap<>();

    @EventHandler
    public void onplace(org.bukkit.event.block.BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        PlayerInfo2 info = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        if (BlockBreakEvt.edit.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
            player.sendMessage("bạn đang trong chế độ edit (ghi cancel xuống chat để exit!)");
        } else if (info.isIg()) {
            if (info.getManager().players.contains(player)) {
                event.setCancelled(true);
            }
        } else if (edit2.containsKey(player.getUniqueId())) {
            if (edit2.get(player.getUniqueId()).split("-")[0].equals("LootChestEdit")) {
                String world = edit2.get(player.getUniqueId()).split("-")[1];
                World realWorld = Bukkit.getWorld(world);
                SetupInv.add(player);
                MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(world);
                if (player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§aChest~~~")) {
                    int x = block.getLocation().getBlockX();
                    int y = block.getLocation().getBlockY();
                    int z = block.getLocation().getBlockZ();
                    if (!mapsConfig.chestParticle.contains(world + " " + x + " " + y + " " + z)) {
                        mapsConfig.chestParticle.add(world + " " + x + " " + y + " " + z);
                        block.setType(Material.CHEST);
                        player.sendMessage("Add Thành Công!");
                    } else {
                        player.sendMessage("Block Da Ton Tai");
                    }
                }
            }
        }
    }
}


















































































































































































































































































































































































































































































































































































































































































































































































































































































































































































































