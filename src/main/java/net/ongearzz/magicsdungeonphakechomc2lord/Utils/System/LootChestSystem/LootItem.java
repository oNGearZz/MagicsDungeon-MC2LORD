package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemData;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.LootChestConfig;
import org.bukkit.inventory.ItemStack;

public class LootItem {
    public static ItemStack getFormLccf(String id) {
        ItemStack stack = ItemData.GetItemStack(LootChestConfig.getConfiguration().getString("Items." + id + ".data"));
        return stack;
    }
}
