package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Tab;

import net.minecraft.server.v1_12_R1.Packet;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerTab {
    private static Set<PlayerTab> playerTabs = new HashSet<>();

    private Player player;

    private Scoreboard scoreboard;

    private List<TabEntry> entries;

    public Player getPlayer() {
        return this.player;
    }

    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }

    public List<TabEntry> getEntries() {
        return this.entries;
    }

    public PlayerTab(final Player player) {
        this.player = player;
        this.entries = new ArrayList<>();
        clear();
        if (!player.getScoreboard().equals(Bukkit.getScoreboardManager().getMainScoreboard())) {
            this.scoreboard = player.getScoreboard();
            assemble();
        } else {
            (new BukkitRunnable() {
                public void run() {
                    PlayerTab.this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
                    player.setScoreboard(PlayerTab.this.scoreboard);
                    PlayerTab.this.assemble();
                }
            }).runTask((MagicsDungeonPhakeChoMC2LORD.getInstance()).getInstance());
        }
        playerTabs.add(this);
    }

    public void clear() {
        for (TabEntry entry : this.entries) {
            if (entry.nms() != null) {
                PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entry.nms());
                (((CraftPlayer) this.player).getHandle()).playerConnection.sendPacket(packet);
            }
        }
        for (Player online : Bukkit.getOnlinePlayers()) {
            PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, ((CraftPlayer) online).getHandle());
            (((CraftPlayer) this.player).getHandle()).playerConnection.sendPacket(packet);
        }
        this.entries.clear();
    }

    private void assemble() {
        for (int i = 0; i < 60; i++) {
            int x = i % 3;
            int y = i / 3;
            (new TabEntry(this, getNextBlank(), x, y)).send();
        }
        Bukkit.getPluginManager().callEvent(new PlayerTabCreateEvent(this));
    }

    public TabEntry getByPosition(int x, int y) {
        for (TabEntry tabEntry : this.entries) {
            if (tabEntry.x() == x && tabEntry.y() == y)
                return tabEntry;
        }
        return null;
    }

    public String getNextBlank() {
        label14:
        for (String string : getAllBlanks()) {
            for (TabEntry tabEntry : this.entries) {
                if (tabEntry.text() != null && tabEntry.text().startsWith(string))
                    continue label14;
            }
            return string;
        }
        return null;
    }

    private static List<String> getAllBlanks() {
        List<String> toReturn = new ArrayList<>();
        for (ChatColor chatColor : ChatColor.values()) {
            toReturn.add(chatColor + "" + ChatColor.RESET);
            for (ChatColor chatColor1 : ChatColor.values()) {
                if (toReturn.size() >= 60)
                    return toReturn;
                toReturn.add(chatColor + "" + chatColor1 + ChatColor.RESET);
            }
        }
        return toReturn;
    }

    public static PlayerTab getByPlayer(Player player) {
        for (PlayerTab playerTab : playerTabs) {
            if (playerTab.getPlayer().getName().equals(player.getName()))
                return playerTab;
        }
        return null;
    }

    public static Set<PlayerTab> getPlayerTabs() {
        return playerTabs;
    }
}
