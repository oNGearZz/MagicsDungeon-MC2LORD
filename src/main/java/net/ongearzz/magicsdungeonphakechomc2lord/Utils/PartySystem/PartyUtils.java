package net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Colored;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Lang;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import sun.awt.image.BufImgSurfaceData;

import java.util.UUID;

public class PartyUtils {

    private static final Lang lang = new Lang();

    public static void createParty(Player leader,String name) {
        if (name != null) {
            Party2 party2 = new Party2(leader, name);
            party2.load();
            MagicsDungeonPhakeChoMC2LORD.getInstance().partyMap.put(leader.getUniqueId(), party2);
            MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(leader).setParty(party2);
        } else {
            Party2 party2 = new Party2(leader, "Fun =))");
            party2.load();
            MagicsDungeonPhakeChoMC2LORD.getInstance().partyMap.put(leader.getUniqueId(), party2);
            MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(leader).setParty(party2);
        }
    }


    public static Party2 getParty(Player player) {
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        return info2.getParty();
    }


    public static void invite(Player leader, Player player) {
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(leader);
        Party2 party2 = info2.getParty();
        if (!party2.getPlayers().contains(player.getUniqueId())) {
            party2.getInInvites().add(player);
            for (int i = 0; i < lang.getThongBaoDaInviteThanhCong().size(); i++) {
                party2.sendMessage(lang.getThongBaoDaInviteThanhCong().get(i).replace("<player>",player.getDisplayName()));
            }
            for (int í = 0; í < lang.getLeaderInvite().size(); í++) {
                player.sendMessage(lang.getLeaderInvite().get(í).replace("<leader>",leader.getDisplayName()));
            }
        } else {
            for (int i = 0; i < lang.getNguoiNayDaTonTaiTrongParty().size(); i++) {
                leader.sendMessage(lang.getNguoiNayDaTonTaiTrongParty().get(i));
            }
        }
    }

    public static void invite(Player leader,Player player, Player sender) {
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(leader);
        Party2 party2 = info2.getParty();
        if (!party2.getPlayers().contains(player.getUniqueId())) {
            party2.getInInvites().add(player);
            for (int i = 0; i < lang.getMemberInvite().size(); i++) {
                player.sendMessage(lang.getMemberInvite().get(i).replace("<sender>",sender.getDisplayName()).replace("<leader>",leader.getName()));
            }
        } else {
            for (int i = 0; i < lang.getI().size(); i++) {
                sender.sendMessage(lang.getI().get(i));
            }
        }

    }
    public static void startQueue(Player player) {
        Party2 party2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player).getParty();
        for (Player players : Bukkit.getOnlinePlayers()) {
            player.sendMessage("§6§m--------------------");
            player.sendMessage("party cua " + party2.getLeader().getName() + " dang queue!");
            setInQueue(party2);
            player.sendMessage("hoac su dung lenh:");
            player.sendMessage("/dp join " + party2.getLeader().getName());
            player.sendMessage("§6§m--------------------");
            return;
        }
    }

    public static void setInQueue(Player player,Party2 party2) {
        TextComponent msg = new TextComponent("Bấm Vào Đây Để Tham Gia!");
        msg.setColor(ChatColor.YELLOW);
        msg.setBold(true);
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/DungeonParty join " + party2.getLeader().getName()));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Bấm Vào Để Tham Gia!").color(ChatColor.YELLOW).italic(true).create()));
        player.spigot().sendMessage(msg);
    }

    public static void setInQueue(Party2 party2) {
        TextComponent msg = new TextComponent("Bấm Vào Đây Để Tham Gia!");
        msg.setColor(ChatColor.YELLOW);
        msg.setBold(true);
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/DungeonParty join " + party2.getLeader().getName()));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Bấm Vào Để Tham Gia!").color(ChatColor.YELLOW).italic(true).create()));
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(msg);
        }
    }


    public static void remove(Player player) {
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        Party2 party2 = info2.getParty();
        party2.removeMember(player.getUniqueId());
    }


    public static void disband(Player leader) {
        MagicsDungeonPhakeChoMC2LORD.getInstance().partyMap.remove(leader.getUniqueId());
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(leader);
        info2.getParty().disband();
    }


    public static void addToParty(Player targer, Player leader) {
        Party2 party2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(leader).getParty();
        if (party2.getInInvites().contains(targer)) {
            party2.addMember(targer);
            party2.getInInvites().remove(targer);
        } else {
            party2.addMember(targer);
        }
    }


    public static void sendList(Player leader, Player sender) {
        Party2 party2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(leader).getParty();
        sender.sendMessage(Colored.msgColor("&6&m---------------"));
        sender.sendMessage(Colored.msgColor("&aLeader: " + party2.getLeader().getName()));
        sender.sendMessage(Colored.msgColor("&aMembers: "));
        for (int i = 0; i < party2.getPlayers().size(); i++) {
            if (Bukkit.getPlayer(party2.getPlayers().get(i)) != party2.getLeader()) {
                sender.sendMessage(Colored.msgColor("&7- &a" + Bukkit.getPlayer(party2.getPlayers().get(i)).getName()));
            }
        }
        sender.sendMessage(Colored.msgColor("&6&m---------------"));
    }

    public static void leave(Party2 party2, Player target, boolean leader) {
        if (!leader) {
            party2.removeMember(target.getUniqueId());
            for (int i = 0; i < lang.getF().size(); i++) {
                party2.sendMessage(lang.getF().get(i).replace("<player>",target.getDisplayName()));
            }
        } else {
            party2.removeMember(target.getUniqueId());
            party2.setLeader(Bukkit.getPlayer(party2.getPlayers().get(0)));
            for (int i = 0; i < lang.getF().size(); i++) {
                party2.sendMessage(lang.getF().get(i).replace("<player>",target.getDisplayName()));
            }
            for (int a = 0; a < lang.getN().size(); a++) {
                party2.sendMessage(lang.getN().get(a).replace("<player>", party2.getLeader().getDisplayName()));
            }
        }
    }


    public static boolean kick(Party2 party2, Player targer) {
        if (party2.getPlayers().contains(targer.getUniqueId())) {
            party2.getPlayers().remove(targer.getUniqueId());
            return true;
        } else {
            return false;
        }
    }


    public static void transfer(Party2 party2, Player newLeader){
        party2.setLeader(newLeader);
    }


    public static boolean mute(Party2 party2) {
        if (party2.isMute()) {
            party2.setMute(false);
            return false;
        } else {
            party2.setMute(true);
            return true;
        }
    }



    public static void chat(Party2 party2, Player sender, String msg) {
        if (party2.isMute()) {
            sender.sendMessage("Party Nay Da Dc Leader Mute!");
        } else {
            for (UUID uuid : party2.getPlayers()) {
                Player player = Bukkit.getPlayer(uuid);
                player.sendMessage(sender.getName() + " >> " + msg);
            }
        }
    }


    public static void kickoffline(Party2 party2,Player target) {
        if (Bukkit.getOnlinePlayers().contains(target)) {
            if (target != party2.getLeader()) {
                party2.removeMember(target.getUniqueId());
                party2.sendMessage("Party >> " + target.getName() + " Da Thoat Khoi Game, Va Bi Kick Khoi Party!");
            } else {
                party2.removeMember(target.getUniqueId());
                party2.setLeader(Bukkit.getPlayer(party2.getPlayers().get(0)));
                party2.sendMessage("Party >> " + target.getName() + " Da Thoat Khoi Game, Va Bi Kick Khoi Party!");
                party2.sendMessage(Bukkit.getPlayer(party2.getPlayers().get(0)).getName() + " Bay Gio Se La Leader Cua Party Nay!");
            }
        }
    }
}