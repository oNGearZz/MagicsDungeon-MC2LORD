package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.MapsConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ActiveWorld;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import sun.jvm.hotspot.opto.MachSafePointNode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BlockBreakEvt implements Listener {
    public static Map<UUID, String> edit = new HashMap<>();
    public static Map<UUID, String> edit2 = new HashMap<>();
    public static Map<UUID, String> edit3 = new HashMap<>();
    public static Map<UUID, String> edit4 = new HashMap<>();
    public static Map<UUID, String> edit5 = new HashMap<>();

    @EventHandler
    public void onbrea(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        if (info2.isIg()) {
            if (info2.getManager() != null) {
                DungeonManager manager = info2.getManager();
                if (ActiveWorld.getGetActiveWorld().contains(player.getWorld().getName())) {
                    if (manager.players.contains(player)) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onbreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location location = block.getLocation();
        if (edit.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
            String world = edit.get(player.getUniqueId()).split(" ")[1];
            String mmname = edit.get(player.getUniqueId()).split(" ")[3];
            int stage = Integer.parseInt(edit.get(player.getUniqueId()).split(" ")[4]);
            MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(world);
            MythicMob mob = MythicMobs.inst().getMobManager().getMythicMob(mmname);
            if (mapsConfig.customSpawn.contains(stage + ";" + mob.getInternalName() + ";" + world + " " + block.getLocation().getBlockX() + " " +
                    block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ())) {
                mapsConfig.customSpawn.remove(stage + ";" + mob.getInternalName() + ";" + world + " " + block.getLocation().getBlockX() + " " +
                        block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ());
                MapsConfig.paticles.remove(block.getLocation());
                player.sendMessage("Remove Thành Công: " + block.getLocation().getBlockX() + " " + block.getLocation().getBlockX() + " " + block.getLocation().getBlockZ());
            } else {
                mapsConfig.customSpawn.add(stage + ";" + mob.getInternalName() + ";" + world + " " + block.getLocation().getBlockX() + " " +
                        block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ());
                MapsConfig.paticles.add(block.getLocation());
                player.sendMessage("Add Thành Công: " + block.getLocation().getBlockX() + " " + block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ());
            }

        } else if (BlockPlaceEvent.edit2.containsKey(player.getUniqueId())) {
            String world = BlockPlaceEvent.edit2.get(player.getUniqueId()).split("-")[1];
            MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(world);
            int x = block.getLocation().getBlockX();
            int y = block.getLocation().getBlockY();
            int z = block.getLocation().getBlockZ();
            if (mapsConfig.chestParticle.contains(world + " " + x + " " + (y + 1) + " " + z)) {
                block.setType(Material.AIR);
                mapsConfig.chestParticle.remove(world + " " + x + " " + (y + 1) + " " + z);
                player.sendMessage("Remove Thanh Cong");
            } else {
                player.sendMessage("block k tồn tại trong cf");
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onbreak2(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Location location = block.getLocation();
        if (edit2.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
            if (edit2.get(player.getUniqueId()).split("-")[0].equals("LootChestEdit")) {
                String world = edit2.get(player.getUniqueId()).split("-")[1];
                World realWorld = Bukkit.getWorld(world);
                int x = block.getLocation().getBlockX();
                int y = block.getLocation().getBlockY();
                int z = block.getLocation().getBlockZ();
                Location loc = new Location(realWorld, x, (y + 1), z);
                MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(world);
                if (loc.getBlock().getType().equals(Material.AIR)) {
                    if (!mapsConfig.chestParticle.contains(world + " " + x + " " + y + " " + z)) {
                        mapsConfig.chestParticle.add(world + " " + x + " " + y + " " + z);
                        MapsConfig.paticle2.add(block.getLocation());
                        player.sendMessage("add thành công!");
                    } else {
                        mapsConfig.chestParticle.remove(world + " " + x + " " + y + " " + z);
                        MapsConfig.paticle2.remove(block.getLocation());
                        player.sendMessage("remove thanh Cong!");
                    }
                } else {
                    player.sendMessage("Block Phía Trên Phải Là Air");
                }
            }
        }
    }

    @EventHandler
    public void onbreak3(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (edit3.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
            if (edit3.get(player.getUniqueId()).split("-")[0].equals("DoorEdit")) {
                String world = edit3.get(player.getUniqueId()).split("-")[1];
                World realworld = Bukkit.getWorld(world);
                int stage = Integer.parseInt(edit3.get(player.getUniqueId()).split("-")[2]);
                MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(world);
                if (!mapsConfig.doors.contains(stage + " " + world + " " + block.getLocation().getBlockX() + " " + block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ())) {
                    mapsConfig.doors.add(stage + " " + world + " " + block.getLocation().getBlockX() + " " +
                            block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ());
                    player.sendMessage("Add Thanh Cong! (ban kinh la 1 block!)");
                } else {
                    mapsConfig.doors.remove(stage + " " + world + " " + block.getLocation().getBlockX() + " " +
                            block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ());
                    player.sendMessage("Remove Thanh Cong!");
                }
            }
        }
    }

//    @EventHandler
//    public void onbreak4(BlockBreakEvent event) {
//        Player player = event.getPlayer();
//        Block block = event.getBlock();
//        if (edit4.containsKey(player.getUniqueId())) {
//            event.setCancelled(true);
//            if (edit4.get(player.getUniqueId()).split("-")[0].equals("SetDoorPhu")) {
//                String world = edit4.get(player.getUniqueId()).split("-")[1];
//                World realworld = Bukkit.getWorld(world);
//                MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(world);
//                if (!mapsConfig.doorPhu.contains(world + " " + block.getLocation().getBlockX() + " " +
//                        block.getLocation().getBlockY() + " " + block.getLocation().getBlockZ())) {
//                    mapsConfig.doorPhu.add(world + " " + block.getLocation().getBlockX() + " " + block.getLocation().getBlockY() + " " +
//                            block.getLocation().getBlockZ());
//                    MapsConfig.doorphuParticle.add(block.getLocation());
//                    player.sendMessage("Add Thanh Cong!");
//                } else {
//                    mapsConfig.doorPhu.remove(world + " " + block.getLocation().getBlockX() + " " + block.getLocation().getBlockY() + " " +
//                            block.getLocation().getBlockZ());
//                    MapsConfig.doorphuParticle.remove(block.getLocation());
//                    player.sendMessage("Remove Thanh Cong!");
//                }
//            }
//        }
//    }

    @EventHandler
    public void onbreak5(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (edit5.containsKey(player.getUniqueId())) {
            event.setCancelled(true);
            String world = edit5.get(player.getUniqueId()).split("-")[0];
            String mmName = edit5.get(player.getUniqueId()).split("-")[1];
            MapsConfig mapsConfig = MagicsDungeonPhakeChoMC2LORD.getInstance().mapsConfig.get(world);
            if (!mapsConfig.boss.contains(world + " " + event.getBlock().getLocation().getBlockX() +
                    " " + event.getBlock().getLocation().getBlockY() + " " + event.getBlock().getLocation().getBlockZ() + " " + mmName)) {
                mapsConfig.boss.add(world + " " + event.getBlock().getLocation().getBlockX() +
                        " " + event.getBlock().getLocation().getBlockY() + " " + event.getBlock().getLocation().getBlockZ() + " " + mmName);
                player.sendMessage("add thanh cong!");
            } else {
                mapsConfig.boss.remove(world + " " + event.getBlock().getLocation().getBlockX() +
                        " " + event.getBlock().getLocation().getBlockY() + " " + event.getBlock().getLocation().getBlockZ() + " " + mmName);
                player.sendMessage("remove thanh cong!");
            }
        }
    }

}






































