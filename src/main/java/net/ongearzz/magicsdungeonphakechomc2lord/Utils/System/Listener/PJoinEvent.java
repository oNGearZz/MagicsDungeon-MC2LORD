package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Npc.NpcCreate;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Npc.PacketReader;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;

public class PJoinEvent implements Listener {

    @EventHandler
    public void onjoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        NpcCreate.sendPacket(player);
        File f1 = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Players", player.getUniqueId() + ".yml");
        if (!f1.exists()) {
            PlayerInfo2 info = new PlayerInfo2(player);
            info.getData().load();
        }
        PacketReader reader = new PacketReader();
        reader.add(player);

        PlayerInfo2 info2 = new PlayerInfo2(player);
        for (Player player1 : Bukkit.getOnlinePlayers()) {
            NpcCreate.sendPacket(player1);
        }
    }

}
