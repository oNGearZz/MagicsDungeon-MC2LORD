package net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PartyDungeon {
    public String leader;
    private String id;
    public boolean inQueue;

    public String getId() {
        return id;
    }

    List<String> players = new ArrayList<>();

    public List<String> getPlayers() {
        return players;
    }

    private MagicsDungeonPhakeChoMC2LORD plugin = MagicsDungeonPhakeChoMC2LORD.getInstance();

    public PartyDungeon(String leader) {
        this.leader = leader;
    }
    public void load() {
        players.add(leader);
        this.id = String.valueOf(System.currentTimeMillis());
        plugin.getS1.put(Bukkit.getPlayer(leader), id);
        plugin.getS3.put(Integer.valueOf(id), this);
    }

    public void addBangCommand(Player sender, String player) {
        if (!players.contains(player)) {
            this.players.add(player);
            plugin.getS1.put(Bukkit.getPlayer(player), id);
        } else {
            sender.sendMessage("Người Này Đã Tồn Tại!");
        }
    }

    public void addBangGUI(String player) {
        if (inQueue) {
            if (!players.contains(player)) {
                if (players.size() <= 4) {
                    this.players.add(player);
                    plugin.getS1.put(Bukkit.getPlayer(player), id);
                    Bukkit.getPlayer(player).sendMessage("Add Thành Công!");
                } else {
                    Bukkit.getPlayer(player).sendMessage("Party Này Đã Đầy!");
                }
            } else {
                Bukkit.getPlayer(player).sendMessage("Bạn Đã Có Trong Party");
            }
        } else {
            Bukkit.getPlayer(player).sendMessage("Party Này Đã Tắt Chế Độ Tuyển Thành viên!");
        }
    }

    public void remove(Player sender, String player) {
        if (players.contains(player)) {
            this.players.remove(player);
            plugin.getS1.remove(Bukkit.getPlayer(player), id);
        } else {
            if (sender != null) {
                sender.sendMessage("Người Này Đã Tồn Tại Trong Party!!");
            }
        }
    }

    public void removeBangConsole(String player) {
        if (players.contains(player)) {
            this.players.remove(player);
            for (String p : players) {
                Player pla = Bukkit.getPlayer(p);
                pla.sendMessage("");
            }
        }
    }

    public void disband() {
        for (String s : players) {
            Player player = Bukkit.getPlayer(s);
            PlayerInfo2 info = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
            info.setParty(null);
            remove(null, s);
            plugin.getS1.remove(player);
        }
        players.clear();
    }
    public void startQueue() {
        inQueue = true;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage("§6§m--------------------");
            player.sendMessage("party cua " + leader + " dang queue!");
            setInQueue(player);
            player.sendMessage("hoac su dung lenh:");
            player.sendMessage("/DP join " + leader);
            player.sendMessage("§6§m--------------------");
            return;
        }
    }

    public void stopQueue() {
        inQueue = false;
    }

    public void setInQueue(Player player) {
        TextComponent msg = new TextComponent("Bấm Vào Đây Để Tham Gia!");
        msg.setColor(ChatColor.YELLOW);
        msg.setBold(true);
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/DungeonParty join " + leader));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Bấm Vào Để Tham Gia!").color(ChatColor.YELLOW).italic(true).create()));
        String s = String.valueOf(msg);
        player.spigot().sendMessage(ChatMessageType.valueOf(s));
    }
}













































