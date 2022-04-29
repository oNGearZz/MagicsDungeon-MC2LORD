package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ActiveWorld;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import org.apache.logging.log4j.core.net.Priority;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

import java.util.ArrayList;
import java.util.List;

public class AntiDefaultMobSpawm implements Listener {
    @EventHandler()
    public void spawm(CreatureSpawnEvent event) {
        List<EntityType> mobs = new ArrayList<>();
        mobs.add(EntityType.ZOMBIE);
        mobs.add(EntityType.SKELETON);
        mobs.add(EntityType.CREEPER);
        mobs.add(EntityType.SPIDER);
        mobs.add(EntityType.WITCH);
        mobs.add(EntityType.ENDERMAN);
        mobs.add(EntityType.ZOMBIE_VILLAGER);
        if (mobs.contains(event.getEntityType())) {
            if (event.getEntity().getCustomName() == null) {
                if (ActiveWorld.getGetActiveWorld().contains(event.getEntity().getWorld().getName())) {
                    event.setCancelled(true);
                }
                if (event.getLocation().getWorld().getName().equals("DungeonWorld1")) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
