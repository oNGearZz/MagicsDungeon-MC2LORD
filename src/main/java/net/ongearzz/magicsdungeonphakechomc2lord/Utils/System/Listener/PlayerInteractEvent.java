package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Lang;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.MapsConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupInv;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import java.awt.Color;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class PlayerInteractEvent implements Listener {
    private Lang lang = new Lang();
    @EventHandler
    public void onract(org.bukkit.event.player.PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Block b = event.getClickedBlock();
        PlayerInfo2 info = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(p);
        Player player = info.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getClickedBlock().getType().equals(Material.COAL_BLOCK) || event.getClickedBlock().getType().equals(Material.REDSTONE_BLOCK)) {
                if (info.isIg()) {
                    DungeonManager manager = info.getManager();
                    List<Location> blocks = manager.blocks;
                    Location location = new Location(Bukkit.getWorld(manager.worldDau), b.getLocation().getBlockX(), b.getLocation().getBlockY(), b.getLocation().getBlockZ());
                    Block block = location.getBlock();
                    if (blocks.contains(block.getLocation())) {
                        if (manager.keyid != null) {
                            if (manager.getConfig().getInt("D2." + block.getLocation()) == manager.keyid) {
                                int stage = manager.getConfig().getInt("D2." + block.getLocation());
                                for (int i = 0; i < manager.getConfig().getStringList("D3." + stage).size(); i++) {
                                    String loc = manager.getConfig().getStringList("D3." + stage).get(i);
                                    String world = loc.split(" ")[0];
                                    World realWorld = Bukkit.getWorld(world);
                                    int x = Integer.parseInt(loc.split(" ")[1]);
                                    int y = Integer.parseInt(loc.split(" ")[2]);
                                    int z = Integer.parseInt(loc.split(" ")[3]);
                                    Location location1 = new Location(realWorld, x, y, z);
                                    manager.exploded2(location1);
                                }
                                manager.stages += 1;
                                manager.startStages(manager.stages);
                                player.sendMessage(lang.getMoDoorThanhCong(stage));
                            } else {
                                player.sendMessage(lang.getKeyError());
                            }
                        } else {
                            player.sendMessage(lang.getKeyError());
                        }
                        if (manager.boss) {
                            player.sendMessage(lang.getMoCuaBossThanhCong());
                        }
                    }
                }
            }
            if (event.getClickedBlock().getType().equals(Material.COBBLESTONE)) {
                if (info.isIg()) {
                    DungeonManager manager = info.getManager();
                    List<Location> blocks = manager.blocksDoorPhu;
                    Location location = new Location(Bukkit.getWorld(manager.worldDau), b.getLocation().getBlockX(), b.getLocation().getBlockY(), b.getLocation().getBlockZ());
                    Block block = location.getBlock();
                    if (blocks.contains(block.getLocation())) {
                        int ia = manager.getConfig().contains("DP1") ? manager.getConfig().getConfigurationSection("DP1").getKeys(false).size() : 0;
                        for (int i = 0; i < ia; i++) {
                            if (manager.getConfig().getString("DP1." + i) != null) {
                                for (int i1 = 0; i1 < manager.getConfig().getStringList("DP1." + i).size(); i1++) {
                                    String loc = manager.getConfig().getStringList("DP1." + i).get(i1);
                                    if (loc.equals(block.getLocation().toString())) {
                                        String lc = manager.getConfig().getStringList("DP2." + i).get(i1);
                                        String world = lc.split(" ")[0];
                                        int x = Integer.parseInt(lc.split(" ")[1]);
                                        int y = Integer.parseInt(lc.split(" ")[2]);
                                        int z = Integer.parseInt(lc.split(" ")[3]);
                                        Location loca = new Location(manager.worldManager.getWorld(), x, y, z);
                                        manager.exploded2(loca);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}








