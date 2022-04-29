package net.ongearzz.magicsdungeonphakechomc2lord.Commands.Setup;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import io.lumine.xikage.mythicmobs.MythicMobs;
import net.minecraft.server.v1_12_R1.*;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Map1.Manager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Colored;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.MapsConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ActiveWorld;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener.BlockBreakEvt;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener.BlockPlaceEvent;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener.PlayerChatEvent;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupGui;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupInv;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.OrbSystem.DungeonOrbStack;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Reward.RewardGUISetup;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_12_R1.CraftServer;
import org.bukkit.craftbukkit.v1_12_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.UUID;

public class SetupCommands implements CommandExecutor {
    public int plsnum(String s) {
        return Integer.valueOf(s).intValue();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] arg) {
        Player player = (Player) sender;
        if (player.hasPermission("MD.SetupCommand")) {
            if (command.getName().equals("DungeonEdit")) {
                if (arg.length == 0) {
                    player.sendMessage("/DungeonEdit CustomSpawn + [World] + [mobName(MythicMobs)] + [Stages]");
                    player.sendMessage("/DungeonEdit + StartParticle");
                    player.sendMessage("/DungeonEdit + StopParticle");
                    player.sendMessage("/DungeonEdit + LootChest");
                    player.sendMessage("/DungeonEdit + SetLootChest + [world]");
                    player.sendMessage("/DungeonEdit + WorldList");
                    player.sendMessage("-------------HDSD--------------");
                    player.sendMessage("Pls Đừng Xài /Reload (xài stop)");
                    player.sendMessage("Muốn Config thì Phải Stop Server! (vì khi load thì sv sẽ replace lại config![mất hết config mới])");
                    player.sendMessage("-------------------------------");
                    return true;
                } else if (arg[0].equals("CustomSpawn")) {
                    if (arg.length == 1) {
                        player.sendMessage("/DungeonEdit CustomSpawn + [World] + [mobName(MythicMobs)] + [Stages]");

                    } else if (arg.length == 2) {
                        player.sendMessage("/DungeonEdit CustomSpawn + [World] + [mobName(MythicMobs)] + [Stages]");

                    } else if (arg.length == 3) {
                        if (MythicMobs.inst().getMobManager().getMythicMob(arg[2]) != null) {
                            player.sendMessage("/DungeonEdit CustomSpawn + [World] + [mobName(MythicMobs)] + [Stages]");
                            return true;
                        } else {
                            player.sendMessage("mob ko hợp lệ");
                            return true;
                        }
                    } else if (arg.length == 4) {
                        try {
                            if (MythicMobs.inst().getMobManager().getMythicMob(arg[2]) != null) {
                                int a = plsnum(arg[3]);
                                if (MagicsDungeonPhakeChoMC2LORD.getInstance().worlds.contains(arg[1])) {
                                    String code = "DungeonEdit " + arg[1] + " " + "arg[1]" + " " + arg[2] + " " + a;
                                    BlockBreakEvt.edit.put(player.getUniqueId(), code);
                                    player.sendMessage("Đập vào block để add!");
                                    player.sendMessage("ghi cancel xuống chat để exit!");
                                    MapsConfig.startParticle = true;
                                }
                                return true;
                            }
                        } catch (Exception e) {
                            player.sendMessage("pls ghi số");
                            return true;
                        }
                    }
                } else if (arg[0].equals("StartParticle")) {
                    if (arg.length == 1) {
                        if (!MapsConfig.startParticle) {
                            MapsConfig.startParticle = true;
                            player.sendMessage("Bật thành công");
                            return true;
                        } else {
                            player.sendMessage("Đã Bật Rồi!");
                            return true;
                        }
                    }
                } else if (arg[0].equals("StopParticle")) {
                    if (arg.length == 1) {
                        if (MapsConfig.startParticle) {
                            MapsConfig.startParticle = false;
                            player.sendMessage("tắt thành công");
                            return true;
                        } else {
                            player.sendMessage("Đã Tắt Rồi!");
                            return true;
                        }
                    }
                } else if (arg[0].equals("LootChest")) {
                    if (arg.length == 1) {
                        SetupGui.customitem(player);
                        return true;
                    }
                } else if (arg[0].equals("SetLootChest")) {
                    if (arg.length == 1) {
                        player.sendMessage("/DungeonEdit SetLootChest + [world]");
                        return true;
                    } else if (arg.length == 2) {
                        if (MagicsDungeonPhakeChoMC2LORD.getInstance().worlds.contains(arg[1])) {
                            BlockBreakEvt.edit2.put(player.getUniqueId(), "LootChestEdit-" + arg[1]);
                            player.sendMessage("Đập vào block để Add Vào LootChestLocation! (World: " + arg[1] + ")");
                            player.sendMessage("ghi cancel xuống chat để exit!");
                        }
                        return true;
                    }
                } else if (arg[0].equals("WorldList")) {
                    if (arg.length == 1) {
                        for (int i = 0; i < MagicsDungeonPhakeChoMC2LORD.getInstance().worlds.size(); i++) {
                            player.sendMessage("- " + MagicsDungeonPhakeChoMC2LORD.getInstance().worlds.get(i));
                        }
                        return true;
                    }
                } else if (arg[0].equals("test")) {
                    if (arg.length == 1) {
//                        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
//                        armorStand.setCustomName("test");
//                        armorStand.setCustomNameVisible(true);
//                        armorStand.setInvulnerable(true);
//                        player.sendMessage(String.valueOf(armorStand.getEntityId()));
//                        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
//                        WorldServer worldServer = ((CraftWorld) player.getWorld()).getHandle();
//                        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "oNGearZz");
//                        EntityPlayer npc = new EntityPlayer(server, worldServer, gameProfile, new PlayerInteractManager(worldServer));
//                        npc.setLocation(player.getLocation().getX() + 0.5, player.getLocation().getY(), player.getLocation().getZ() + 0.5, 2500F, 0F);
//                        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
//                        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc));
//                        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
//                        new BukkitRunnable() {
//                            @Override
//                            public void run() {
//                                PacketPlayOutEntityDestroy deadArmorstand = new PacketPlayOutEntityDestroy(npc.getBukkitEntity().getEntityId());
//                                ((CraftPlayer) player).getHandle().playerConnection.sendPacket(deadArmorstand);
//                            }
//                        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(),0,0);
                        Location location = player.getLocation();
//                                t = t + 0.05 * Math.PI;
//                                for (double theta = 0 ; theta <= 5 * Math.PI; theta = theta + Math.PI/4) {
//                                    double x = t*Math.cos(theta);
//                                    double y = 10*Math.exp(-0.5*t) * Math.sin(t) + 1;
//                                    double z = t*Math.sin(theta);
//                                    location.add(x,y,z);
//                                    player.getWorld().spawnParticle(Particle.FLAME, location,0,0,0,0,1);
//                                    location.subtract(x,y,z);
//                                }
//                                if (t > 4) {
//                                    this.cancel();
//                                }
                        ArmorStand stand1 = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                        ArmorStand stand2 = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                        ArmorStand stand3 = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                        stand1.setInvulnerable(true);
                        stand1.setGravity(false);
                        stand1.setCustomName("stand1");
                        stand1.setCustomNameVisible(true);

                        stand2.setInvulnerable(true);
                        stand2.setGravity(false);
                        stand2.setCustomName("stand2");
                        stand2.setCustomNameVisible(true);

                        stand3.setInvulnerable(true);
                        stand3.setGravity(false);
                        stand3.setCustomName("stand3");
                        stand3.setCustomNameVisible(true);
                        Location l1 = player.getLocation();
                        Location l2 = player.getLocation();
                        new BukkitRunnable() {
                            double t = 0;

                            @Override
                            public void run() {
                                t = t + 0.05 * Math.PI;
                                for (double theta = 0; theta <= 5 * Math.PI; theta = theta + Math.PI / 2) {
                                    double x = t * Math.cos(theta);
                                    double y = 15 * Math.exp(-0.5 * t) * Math.sin(t) + 1;
                                    double z = t * Math.sin(theta);
                                    location.add(x, y, z);
                                    stand1.teleport(location);
                                    location.subtract(x,y,z);

//                                    l1.add(x, y, z);
//                                    stand2.teleport(l1);
//                                    l1.subtract(x,y,z);
//                    location.add(x,y,z);
//                    player.getWorld().spawnParticle(Particle.FLAME, location,0,0,0,0,1);
//                    location.subtract(x,y,z);
                                    // + Math.PI / 3
                                }
                                for (double theta = 0; theta <= 5 * Math.PI; theta = theta + 6.7) {
                                    double x = t * Math.cos(theta);
                                    double y = 15 * Math.exp(-0.5 * t) * Math.sin(t) + 1;
                                    double z = t * Math.sin(theta);
                                    l1.add(x, y, z);
                                    stand2.teleport(l1);
                                    l1.subtract(x, y, z);
                                }
                                for (double theta = 0; theta <= 5 * Math.PI; theta = theta + 5.85) {
                                    double x = t * Math.cos(theta);
                                    double y = 15 * Math.exp(-0.5 * t) * Math.sin(t) + 1;
                                    double z = t * Math.sin(theta);
                                    l2.add(x, y, z);
                                    stand3.teleport(l2);
                                    l2.subtract(x, y, z);
                                }
                                if (t > 4) {
                                    this.cancel();
                                }
                            }
                        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 2);
                        return true;
                    }
                } else if (arg[0].equals("SetDoor")) {
                    if (arg.length == 1) {
                        player.sendMessage("/DungeonEdit SetDoor + [world] + [stage]");
                        return true;
                    } else if (arg.length == 2) {
                        if (MagicsDungeonPhakeChoMC2LORD.getInstance().worlds.contains(arg[1])) {
                            player.sendMessage("/DungeonEdit SetDoor + [world] + [stage]");
                        } else {
                            player.sendMessage("World ko hop le");
                        }
                        return true;
                    } else if (arg.length == 3) {
                        if (MagicsDungeonPhakeChoMC2LORD.getInstance().worlds.contains(arg[1])) {
                            try {
                                int stage = plsnum(arg[2]);
                                BlockBreakEvt.edit3.put(player.getUniqueId(), "DoorEdit-" + arg[1] + "-" + stage);
                                player.sendMessage("Đập vào block để ADd Vào DoorLocation! (World: " + arg[1] + ")");
                                player.sendMessage("ghi cancel xuống chat để exit!");
                            } catch (Exception e) {
                                player.sendMessage("pls ghi so!");
                            }
                        }
                        return true;
                    }
                } else if (arg[0].equals("boss")) {
                    if (arg.length == 1) {
                        player.sendMessage("/DungeonEdit boss + [World] + [MobName]");
                        return true;
                    } else if (arg.length == 2) {
                        if (MagicsDungeonPhakeChoMC2LORD.getInstance().worlds.contains(arg[1])) {
                            player.sendMessage("/DungeonEdit boss + [World] + [MobName]");
                            return true;
                        } else {
                            player.sendMessage("World ko hop le");
                            return true;
                        }
                    } else if (arg.length == 3) {
                        if (MagicsDungeonPhakeChoMC2LORD.getInstance().worlds.contains(arg[1])) {
                            if (MythicMobs.inst().getMobManager().getMythicMob(arg[2]) != null) {

                                BlockBreakEvt.edit5.put(player.getUniqueId(), arg[1] + "-" + arg[2]);
                                player.sendMessage("Đập vào block để Add! (World: " + arg[1] + ")");
                                player.sendMessage("ghi cancel xuống chat để exit!");

                                return true;
                            } else {
                                player.sendMessage("Mob ko hop le");
                            }
                        } else {
                            player.sendMessage("Wolrd ko hop le");
                        }
                    }
                } else if (arg[0].equals("reward")) {
                    if (arg.length == 1) {
                        player.sendMessage("/dungeonedit reward + [score(S,A,B,C,D){tip: viết hoa lên}]");
                        return true;
                    } else if (arg.length == 2) {
                        if (arg[1].equals("S")) {
                            RewardGUISetup.open(player, "S");

                        } else if (arg[1].equals("A")) {
                            RewardGUISetup.open(player, "A");

                        } else if (arg[1].equals("B")) {
                            RewardGUISetup.open(player, "B");

                        } else if (arg[1].equals("C")) {
                            RewardGUISetup.open(player, "C");

                        } else if (arg[1].equals("D")) {
                            RewardGUISetup.open(player, "D");

                        } else {
                            player.sendMessage("score k hop le");
                        }
                        return true;
                    }
                } else {
                    player.sendMessage("Command ko hop le (tip: viết hoa)");
                    return true;
                }
            }
        } else {
            player.sendMessage(Colored.msgColor("&8(&4!&8)&c Bạn Không Có Quyền Xài Lệnh Này!"));
            return true;
        }
        return true;
    }
}









































