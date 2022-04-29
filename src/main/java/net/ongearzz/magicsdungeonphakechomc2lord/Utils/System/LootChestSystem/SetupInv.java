package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem;

import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemData;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener.BlockPlaceEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetupInv {

    public static Map<Player, String> datas = new HashMap<>();
    private static List<Player> dataPlayer = new ArrayList<>();

    public static ItemStack getIs() {
        String value = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzM5MGVlZGUzODFiYjg0NDdmN2Q3MmUxNWI1NjM0NzY4M2UwMmMxN2M5YjhmYzZiZWNkNzI2ZjBhNTJjN2ZjMSJ9fX0=";
        ItemStack stack = new ItemBuilder(ItemBuilder.SkullWithValue(value))
                .setDisplayName("§aChest~~~")
                .addLore("§aClick Vào Chỗ Cần Đặt LootChest!")
                .build();
        return stack;
    }

    public static void add(Player player) {
        String data = ItemData.itemStackArrayToBase64(player.getInventory().getContents());
        datas.put(player, data);
        dataPlayer.add(player);
        Inventory inventory = player.getInventory();
        for (int i = 0; i < player.getInventory().getSize(); i++) {
            inventory.setItem(i, getIs());
        }
    }

    public static void remove(Player player) {
        try {
            if (datas.containsKey(player)) {
                String data = datas.get(player);
                dataPlayer.remove(player);
                datas.remove(player);
                player.getInventory().clear();
                BlockPlaceEvent.edit2.remove(player.getUniqueId());
                ItemStack[] olddata = ItemData.itemStackArrayFromBase64(data);
                player.getInventory().clear();
                player.getInventory().setContents(olddata);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeALL() {
        for (int i = 0; i < dataPlayer.size(); i++) {
            Player player = dataPlayer.get(i);
            remove(player);
        }
    }

}
