package net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.PartyDungeon;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Map;

public class Queue {

    private PartyDungeon party;

    public PartyDungeon getParty() {
        return party;
    }

    public Queue(PartyDungeon party) {
        this.party = party;
        MagicsDungeonPhakeChoMC2LORD.getInstance().queues.put(party, this);
    }
    public void create() {
        MagicsDungeonPhakeChoMC2LORD.getInstance().inQueue.add(this);
        for (Player player : Bukkit.getOnlinePlayers()) {
            TextComponent msg = new TextComponent("Bấm Vào Đây Để Tham Gia!");
            msg.setColor(ChatColor.YELLOW);
            msg.setBold(true);
            msg.setClickEvent( new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/DungeonParty join " + getParty().leader));
            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Bấm Vào Để Tham Gia!").color(ChatColor.YELLOW).italic(true).create()));
            String s = String.valueOf(msg);
            player.spigot().sendMessage(ChatMessageType.valueOf(s));
        }
    }
    public void reCreate() {

    }
    public void cancel() {

    }
}


































