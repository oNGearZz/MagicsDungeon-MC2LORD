package net.ongearzz.magicsdungeonphakechomc2lord.Commands;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.PartyUtils;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PartyCommand_DPC implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player sender = (Player) commandSender;
        PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(sender);
        if (command.getName().equals("dpc")) {
            if (args.length == 0) {
                sender.sendMessage("/dpc + [message]");
                return true;
             } else {
                if (info2.getParty() != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (String arg : args) {
                        stringBuilder.append(arg).append(" ");
                    }
                    PartyUtils.chat(info2.getParty(), sender, stringBuilder.toString());
                }
            }
            return true;
        }
        return true;
    }
}
