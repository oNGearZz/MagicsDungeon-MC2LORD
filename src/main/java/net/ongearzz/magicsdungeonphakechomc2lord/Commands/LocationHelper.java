package net.ongearzz.magicsdungeonphakechomc2lord.Commands;

import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LocationHelper implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        player.getInventory().addItem(getItem());
        return true;
    }

    public ItemStack getItem() {
        ItemStack s = new ItemBuilder(Material.BLAZE_ROD).setDisplayName("Click To De Co Location").build();
        return s;
    }
}
