package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.MapsConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Npc.NpcCreate;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Npc.PacketReader;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.PartyDungeon;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.PartyUtils;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupInv;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Tab.PlayerTab;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Tab.PlayerTabRemoveEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scoreboard.Team;

import java.util.HashSet;

public class PQuitEvent implements Listener {
    @EventHandler
    public void onleft(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        PacketReader reader = new PacketReader();
        reader.remove(player);
        if (MapsConfig.dataInv.containsKey(player.getName())) {
            MapsConfig.remove(player);
        }
        if (BlockPlaceEvent.edit2.containsKey(player.getUniqueId())) {
            SetupInv.remove(player);
        }
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            NpcCreate.sendPacket(player1);
        }
        if (info2.getManager() != null) {
            DungeonManager manager = info2.getManager();
            manager.removePlayer(player);
        }
        if (info2.getParty() != null) {
            PartyUtils.kickoffline(info2.getParty(), player);
        }
    }
}
