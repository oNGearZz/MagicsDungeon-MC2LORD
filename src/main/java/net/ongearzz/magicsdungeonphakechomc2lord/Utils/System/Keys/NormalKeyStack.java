package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Keys;

import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NormalKeyStack {
    public static ItemStack getStack() {
        ItemStack stack = new ItemBuilder(Material.COAL_BLOCK).build();
        return stack;
    }

    public void getArmorStandStack(Player player, Location location) {
        ArmorStand armorStand = (ArmorStand) player.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGravity(false);
        armorStand.setCustomName("Test Name");
        armorStand.setCanPickupItems(false);
        armorStand.setHelmet(getStack());
        armorStand.setInvulnerable(true);
        armorStand.setVisible(false);
    }
}
