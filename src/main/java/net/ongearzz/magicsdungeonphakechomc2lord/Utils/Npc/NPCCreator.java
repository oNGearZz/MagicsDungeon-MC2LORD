package net.ongearzz.magicsdungeonphakechomc2lord.Utils.Npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_12_R1.*;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NPCCreator {

    public static List<EntityPlayer> getNpcs() {
        return npcs;
    }

    private static List<EntityPlayer> npcs = new ArrayList<>();

    public static void createNormalNPC(Player player, String name) {
        MinecraftServer nmsServer = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
        EntityPlayer npc = new EntityPlayer(nmsServer, nmsWorld, new GameProfile(player.getUniqueId(), name), new PlayerInteractManager(nmsWorld));
        npc.setLocation(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 2500F, 0F);
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        npcs.add(npc);
    }

    public static void createNpc(Player player, String name) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), name);
        EntityPlayer npc = new EntityPlayer(server, worldServer, gameProfile, new PlayerInteractManager(worldServer));
        npc.setLocation(player.getLocation().getX() + 0.5, player.getLocation().getY(), player.getLocation().getZ() + 0.5, 2500F, 0F);
        addNPCPacket(npc);
        npcs.add(npc);
    }

    public static void addNPCPacket(EntityPlayer npc) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            double y = Double.parseDouble("-" + (npc.yaw % 360.0) * 256 / 360);
            Packet<?> packet1 = new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) y, (byte) Double.parseDouble("-" + ((npc.pitch % 360.0) * 256 / 360)), false),
                    packet2 = new PacketPlayOutEntityHeadRotation(npc, (byte) y);
            new BukkitRunnable() {
                @Override
                public void run() {
                    connection.sendPacket(packet1);
                    connection.sendPacket(packet2);
                }
            }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 0);
        }
    }

    public static void addJoinPacket(Player player) {
        for (EntityPlayer npc : npcs) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
            double y = Double.parseDouble("-" + (npc.yaw % 360.0) * 256 / 360);
            Packet<?> packet1 = new PacketPlayOutEntity.PacketPlayOutEntityLook(npc.getId(), (byte) y, (byte) Double.parseDouble("-" + ((npc.pitch % 360.0) * 256 / 360)), false),
                    packet2 = new PacketPlayOutEntityHeadRotation(npc, (byte) y);
            new BukkitRunnable() {
                @Override
                public void run() {
                    connection.sendPacket(packet1);
                    connection.sendPacket(packet2);
                }
            }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 0);
        }
    }
}
