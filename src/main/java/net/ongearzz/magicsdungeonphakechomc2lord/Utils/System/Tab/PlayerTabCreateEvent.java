package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Tab;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerTabCreateEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    private PlayerTab playerTab;

    private Player player;

    public PlayerTab getPlayerTab() {
        return this.playerTab;
    }

    public Player getPlayer() {
        return this.player;
    }

    public PlayerTabCreateEvent(PlayerTab playerTab) {
        this.player = playerTab.getPlayer();
        this.playerTab = playerTab;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
