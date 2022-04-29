package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Tab;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.MinecraftServer;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_12_R1.PlayerInteractManager;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.UUID;

public class TabEntry {
    private PlayerTab playerTab;

    private int x;

    private int y;

    private String text;

    private EntityPlayer nms;

    private Team team;

    private boolean setup;

    public TabEntry playerTab(PlayerTab playerTab) {
        this.playerTab = playerTab;
        return this;
    }

    public TabEntry x(int x) {
        this.x = x;
        return this;
    }

    public TabEntry y(int y) {
        this.y = y;
        return this;
    }

    public TabEntry text(String text) {
        this.text = text;
        return this;
    }

    public TabEntry nms(EntityPlayer nms) {
        this.nms = nms;
        return this;
    }

    public TabEntry team(Team team) {
        this.team = team;
        return this;
    }

    public TabEntry setup(boolean setup) {
        this.setup = setup;
        return this;
    }

    public PlayerTab playerTab() {
        return this.playerTab;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public String text() {
        return this.text;
    }

    public EntityPlayer nms() {
        return this.nms;
    }

    public Team team() {
        return this.team;
    }

    public TabEntry(PlayerTab playerTab, String text, int x, int y) {
        this.playerTab = playerTab;
        this.text = text;
        this.x = x;
        this.y = y;
        playerTab.getEntries().add(this);
    }

    private TabEntry setup() {
        this.setup = true;
        Player player = this.playerTab.getPlayer();
        CraftPlayer craftplayer = (CraftPlayer) player;
        this.nms = new EntityPlayer(MinecraftServer.getServer(), ((CraftWorld) player.getWorld()).getHandle(), new GameProfile(UUID.randomUUID(), ChatColor.translateAlternateColorCodes('&', this.text)), new PlayerInteractManager(((CraftWorld) player.getWorld()).getHandle()));
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.UPDATE_DISPLAY_NAME, this.nms);
        (craftplayer.getHandle()).playerConnection.sendPacket(packet);
        this.team = this.playerTab.getScoreboard().registerNewTeam(UUID.randomUUID().toString().substring(0, 16));
        this.team.addEntry(this.nms.getName());
        return this;
    }

    public TabEntry send() {
        if (!this.setup)
            return setup();
        this.text = ChatColor.translateAlternateColorCodes('&', this.text);
        if (this.text.length() > 16) {
            this.team.setPrefix(this.text.substring(0, 16));
            String suffix = ChatColor.getLastColors(this.team.getPrefix()) + this.text.substring(16, this.text.length());
            if (suffix.length() > 16) {
                if (suffix.length() <= 16) {
                    suffix = this.text.substring(16, this.text.length());
                    this.team.setSuffix(suffix.substring(0, suffix.length()));
                } else {
                    this.team.setSuffix(suffix.substring(0, 16));
                }
            } else {
                this.team.setSuffix(suffix);
            }
        } else {
            this.team.setPrefix(this.text);
            this.team.setSuffix("");
        }
        return this;
    }
}
