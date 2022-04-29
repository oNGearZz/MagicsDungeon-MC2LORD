package net.ongearzz.magicsdungeonphakechomc2lord.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("DungeonStatus")) {
            if (player.hasPermission("Dungeon.Admin")) {
                if (args.length == 0) {
                    player.sendMessage("");
                }
            }
        }
        return true;
    }
}
