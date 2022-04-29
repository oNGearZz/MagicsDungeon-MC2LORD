package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.OrbSystem;

import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DungeonOrbStack {
    public static ItemStack getStack() {
        ItemStack stack = new ItemBuilder(ItemBuilder.SkullWithValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmFkYzRhMDI0NzE4ZDQwMWVlYWU5ZTk1YjNjOTI3NjdmOTE2ZjMyM2M5ZTgzNjQ5YWQxNWM5MjY1ZWU1MDkyZiJ9fX0="))
                .setDisplayName("§eDungeon Orb")
                .addLore("")
                .build();
        return stack;
    }
}
