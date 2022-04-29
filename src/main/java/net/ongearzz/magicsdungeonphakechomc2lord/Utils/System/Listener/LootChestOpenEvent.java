package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.MapsConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ActiveWorld;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.RandomChestSystem;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.block.DoubleChest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Random;

public class LootChestOpenEvent implements Listener {
    @EventHandler
    public void onopen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Chest) {
            Chest chest = (Chest) holder;
            if (ActiveWorld.getGetActiveWorld().contains(chest.getLocation().getWorld().getName())) {
                PlayerInfo2 info = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
                if (info.isIg()) {
                    if (info.getManager() != null) {
                        DungeonManager manager = info.getManager();
                        MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(manager.worldDau);
                        String worldname = mapsConfig.getName();
                        String chestworld = chest.getLocation().getWorld().getName();
                        int x = chest.getLocation().getBlockX();
                        int y = chest.getLocation().getBlockY();
                        int z = chest.getLocation().getBlockZ();
                        if (mapsConfig.chestParticle.contains(worldname + " " + x + " " + (y - 1) + " " + z)) {
                            if (manager.chestDaMo.contains(chest.getLocation())) return;
                            RandomChestSystem.fill(chest.getBlockInventory());
                            manager.chestDaMo.add(chest.getLocation());
                        }
                    }
                }
            }
        } else if (holder instanceof DoubleChest) {
            DoubleChest chest = (DoubleChest) holder;
            if (ActiveWorld.getGetActiveWorld().contains(chest.getLocation().getWorld().getName())) {
                PlayerInfo2 info = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
                if (info.isIg()) {
                    if (info.getManager() != null) {
                        DungeonManager manager = info.getManager();
                        MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(manager.worldDau);
                        String worldname = mapsConfig.getName();
                        String chestworld = chest.getLocation().getWorld().getName();
                        int x = chest.getLocation().getBlockX();
                        int y = chest.getLocation().getBlockY();
                        int z = chest.getLocation().getBlockZ();
                        if (mapsConfig.chestParticle.contains(worldname + " " + x + " " + (y - 1) + " " + z)) {
                            if (manager.chestDaMo.contains(chest.getLocation())) return;
                            RandomChestSystem.fill(chest.getInventory());
                            manager.chestDaMo.add(chest.getLocation());
                        }
                    }
                }
            }
        }
    }
}
