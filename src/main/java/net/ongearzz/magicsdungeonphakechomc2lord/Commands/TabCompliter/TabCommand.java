package net.ongearzz.magicsdungeonphakechomc2lord.Commands.TabCompliter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class TabCommand implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equals("MagicsDungeon")) {

        }
        return null;
    }
}
