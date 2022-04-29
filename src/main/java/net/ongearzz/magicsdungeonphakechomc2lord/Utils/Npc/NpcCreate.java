package net.ongearzz.magicsdungeonphakechomc2lord.Utils.Npc;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_12_R1.*;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NpcCreate {
    public static List<EntityPlayer> npcs = new ArrayList<>();

    public static void createDungeonNpc1(Player player, Location location, String NpcSkin) {
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer worldServer = ((CraftWorld) player.getWorld()).getHandle();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), NpcSkin);
        EntityPlayer npc = new EntityPlayer(server, worldServer, gameProfile, new PlayerInteractManager(worldServer));
        npc.setCustomNameVisible(false);
        npc.getBukkitEntity().setCustomNameVisible(false);
        npcs.add(npc);
        npc.setLocation(location.getX() + 0.5, location.getY(), location.getZ() + 0.5, 2500F, 0F);
        final PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
        new BukkitRunnable() {
            public void run() {
//                PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
//               // connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
//                connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte)(npc.getBukkitEntity().getLocation().getYaw() * 256 / 360)));
//                PacketPlayOutNamedEntitySpawn npcz = new PacketPlayOutNamedEntitySpawn(npc);
//                try {
//                    Field field = npcz.getClass().getDeclaredField("a");
//                    field.setAccessible(true);
//                    field.setInt(npcz, npc.getId());
//                    field.setAccessible(false);
//
//                    Field field2 =  npcz.getClass().getDeclaredField("h");
//                    field2.setAccessible(true);
//                    DataWatcher watcher = (DataWatcher) field2.get(npcz);
//                    //changeName
//                    watcher.set(new DataWatcherObject<>(npc.getId(), DataWatcherRegistry.d), "test");
//                    //or hidename
//                    //watcher.set(new DataWatcherObject<>(3, DataWatcherRegistry.h), false);
//                    field2.set(npcz, watcher);
//                    field2.setAccessible(false);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                connection.sendPacket(npcz);
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 0);
        Bukkit.getScheduler().runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), () -> connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc)), 20, 20);
    }

    public static void sendPacket(Player player) {
        for (EntityPlayer npc : npcs) {
            final PlayerConnection connection = (((CraftPlayer)player).getHandle()).playerConnection;
            new BukkitRunnable() {
                public void run() {
//                    connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
//                    connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (npc.yaw * 256 / 360)));
                }
            }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 0);
            Bukkit.getScheduler().runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), () -> connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, npc)), 20, 20);
        }
    }
}
