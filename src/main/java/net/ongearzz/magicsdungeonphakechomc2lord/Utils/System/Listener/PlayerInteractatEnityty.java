package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.minecraft.server.v1_12_R1.PacketPlayOutEntityHeadRotation;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS.IGG1;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ActiveWorld;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.ZombieVillager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractatEnityty implements Listener {

    @EventHandler
    public void oninteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        if (entity instanceof ArmorStand) {
            if (ActiveWorld.getGetActiveWorld().contains(entity.getWorld().getName())) {
                if (info2.isIg()) {
                    if (info2.getManager() != null) {
                        DungeonManager manager = info2.getManager();
                        if (entity.getCustomName().equals("§6Reward Chest")) {
                            event.setCancelled(true);
                            manager.rewardGUI(player);
                        }
                        event.setCancelled(true);
                    }
                }
            }
        } else if (entity instanceof ZombieVillager) {
            if (ActiveWorld.getGetActiveWorld().contains(entity.getWorld().getName())) {
                if (!info2.isIg()) {
                    if (info2.getManager() != null) {
                        DungeonManager manager = info2.getManager();
                        if (entity.getCustomName().equals("§cDungeon Manager")) {
                            event.setCancelled(true);
                            IGG1.open(player, manager);
                        }
                    }
                }
            }
        }
    }
}
