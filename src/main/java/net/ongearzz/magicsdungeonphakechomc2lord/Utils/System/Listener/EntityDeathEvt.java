package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMobDeathEvent;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ActiveWorld;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class EntityDeathEvt implements Listener {
    @EventHandler
    public void ondeath(MythicMobDeathEvent event) {
        Player killer = (Player) event.getKiller();
        Entity entity = event.getEntity();
        if (ActiveWorld.getGetActiveWorld().contains(entity.getWorld().getName())) {
            PlayerInfo2 info = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(killer);
            Player player = info.getPlayer();
            DungeonManager manager = info.getManager();
            if (event.getKiller() != null && event.getKiller() instanceof Player && manager.players.contains(killer)) {

                for (int i = 0; i < manager.mobsCanGiet.size(); i++) {

                    if (manager.mobsCanGiet.get(i).split(";")[0].equalsIgnoreCase(String.valueOf(manager.stages))) {

                        if (manager.mobsCanGiet.contains(manager.stages + ";" + event.getMob().getUniqueId())) {

                            manager.mobsCanGiet.remove(manager.stages + ";" + event.getMob().getUniqueId());

                            if (manager.mobsCanGiet.size() == 0) {

                                if (manager.boss) {

                                    manager.rewardMiniBoss();

                                    manager.miniBossDeath = true;

                                }

                                if ((manager.stages + 1) <= (manager.maxStage - 1)) {

                                    manager.dropKey((manager.stages + 1), event.getEntity().getLocation(), false);

                                } else {

                                    if (!manager.boss) {

                                        manager.dropKey((manager.stages + 1), event.getEntity().getLocation(), true);

                                        String r = manager.getConfig().getStringList("Mobs").get(0);

                                        manager.getConfig().set("Mobs." + r, null);

                                        manager.saveFile();

                                    }

                                }

                            }

                        }

                    }

                }

                for (int j = 0; j < manager.bossMob.size(); j++) {

                    if (manager.bossMob.contains(event.getMob().getUniqueId())) {

                        manager.bossMob.remove(event.getMob().getUniqueId());

                        if (manager.b) {

                            if (manager.bossMob.size() == 0) {

                                manager.reward();

                            }

                        }

                    }

                }

            }

        }

    }

}
