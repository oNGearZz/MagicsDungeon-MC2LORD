package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System;

import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;

import javax.xml.stream.Location;
import java.io.File;
import java.util.*;

public class MobsSpawmEvent {
    private String did;
    private DungeonManager manager;
    private Map<UUID, String> activeMobs = new HashMap<>();
    private Map<Integer, List<UUID>> stagesmob = new HashMap<>();

    public MobsSpawmEvent(String did, DungeonManager manager) {
        this.did = did;
        this.manager = manager;
    }

    public void a(List<String> mobs, List<Location> locations, int stages) {
        List<UUID> mobsList = new ArrayList<>();
        for (int i = 0; i < mobs.size(); i++) {
            ActiveMob activeMob = MythicMobs.inst().getMobManager().spawnMob(mobs.get(i), (org.bukkit.Location) locations.get(i));
            UUID idMob = activeMob.getEntity().getUniqueId();
            mobsList.add(idMob);
            activeMobs.put(idMob, String.valueOf(stages));
        }
        stagesmob.put(stages, mobsList);
    }
}
