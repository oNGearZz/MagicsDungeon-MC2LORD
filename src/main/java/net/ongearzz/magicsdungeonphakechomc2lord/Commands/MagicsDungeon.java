package net.ongearzz.magicsdungeonphakechomc2lord.Commands;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MagicsDungeon implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.sendMessage("§6§m---------- §e§lM§eagics§e§lD§eungeon§6§m ----------");
        player.sendMessage("§aName: §6MagicsDungeon");
        player.sendMessage("§aAuthor:§6 " + MagicsDungeonPhakeChoMC2LORD.getInstance().getAuthor());
        player.sendMessage("§aVerion:§6 0.1B-MC2LORD");
        player.sendMessage("§6§m---------- §e§lM§eagics§e§lD§eungeon§6§m ----------");
        return true;
    }
}
