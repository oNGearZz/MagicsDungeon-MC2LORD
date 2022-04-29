package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerShutDownEvent extends Event {
    public HandlerList getHandlers() {
        return handlers;
    }

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
