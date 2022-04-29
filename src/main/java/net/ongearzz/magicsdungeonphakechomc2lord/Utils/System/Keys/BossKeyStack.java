package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Keys;

import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BossKeyStack {
    public static ItemStack getStack() {
        ItemStack stack = new ItemBuilder(Material.REDSTONE_BLOCK).build();
        return stack;
    }
}
