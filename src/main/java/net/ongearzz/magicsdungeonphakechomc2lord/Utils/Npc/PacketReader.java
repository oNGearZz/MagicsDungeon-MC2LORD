package net.ongearzz.magicsdungeonphakechomc2lord.Utils.Npc;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_12_R1.EntityPlayer;
import net.minecraft.server.v1_12_R1.PacketPlayInUseEntity;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS.IGG1;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Events.NpcInteractEventt;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.lang.reflect.Field;
import java.util.*;

public class PacketReader {
    private Channel channel;

    public Map<UUID, Channel> channels = new HashMap<>();

    public void add(Player player) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        channel = craftPlayer.getHandle().playerConnection.networkManager.channel;
        channels.put(craftPlayer.getUniqueId(), channel);

        if (channel.pipeline().get("PacketInjector") != null)
            return;
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<PacketPlayInUseEntity>() {
            @Override
            protected void decode(ChannelHandlerContext channelHandlerContext, PacketPlayInUseEntity packet, List<Object> list) throws Exception {
                list.add(packet);
                if (packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {
                    if (getValue(packet, "action").toString().equalsIgnoreCase("ATTACK"))
                        return;
                    if (getValue(packet, "action").toString().equalsIgnoreCase("INTERACT_AT"))
                        return;
                    if (getValue(packet, "d").toString().equalsIgnoreCase("OFF_HAND"))
                        return;
                    int id = (int) getValue(packet, "a");
                    if (getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")) {
                        for (EntityPlayer npc : NpcCreate.npcs) {
                            if (npc.getId() == id) {
                                Bukkit.getScheduler().scheduleSyncDelayedTask(MagicsDungeonPhakeChoMC2LORD.getInstance(), new Runnable() {
                                    @Override
                                    public void run() {
                                        PlayerInfo2 playerInfo2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
                                        if (npc.getName().equalsIgnoreCase("Dungeon Manager")) {
                                            if (!playerInfo2.isIg()) {
                                                IGG1.open(player, playerInfo2.getManager());
                                            }
                                        }
                                    }
                                }, 0);
                            }
                        }
                    }
                }
            }
        });
    }

    public void remove(Player player) {
        if (channels.get(player.getUniqueId()) != null) {
            channel = channels.get(player.getUniqueId());
            if (channel.pipeline().get("PacketInjector") != null)
                channel.pipeline().remove("PacketInjector");
        }
    }

    private Object getValue(Object instance, String name) {
        Object result = null;
        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            result = field.get(instance);
            field.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
