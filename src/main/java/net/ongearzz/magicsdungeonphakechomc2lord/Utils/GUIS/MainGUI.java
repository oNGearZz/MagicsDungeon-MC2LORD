package net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem.Level;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem.LevelConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class MainGUI implements Listener {
    public static void open(Player player) {
        PlayerInfo2 info = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        if (info.getData().getPlayerDataConfig().getString("Class").equals("NONE")) {
            ClassOptionGui.open(player);
        } else {
            Inventory inventory = Bukkit.createInventory(null, 54, "Cổng Dungeons!");
            {
                for (int i = 0; i < inventory.getSize(); i++) {
                    inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE)
                            .setDisplayName("§a")
                            .build());
                }
                inventory.setItem(49, new ItemBuilder(Material.BARRIER).setDisplayName("§cTắt!")
                        .build());
                inventory.setItem(13, new ItemBuilder(ItemBuilder.SkullWithValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFhOTQ2M2ZkM2M0MzNkNWUxZDlmZWM2ZDVkNGIwOWE4M2E5NzBiMGI3NGRkNTQ2Y2U2N2E3MzM0OGNhYWIifX19"))
                        .setDisplayName("§a Tầng §7- §a1")
                        .addLore("§7 Kích Cỡ Map:§b Rộng")
                        .addLore("§7 Người Chơi Trung Bình:§b 1-5")
                        .addLore("")
                        .addLore("§7 Boss:§b oNGearZz")
                        .addLore("")
                        .addLore("§7 Yêu Cầu:§b không")
                        .addLore("")
                        .addLore("§e Bấm Vào Để Tìm Đồng Đội!")
                        .build());
                inventory.setItem(48, new ItemBuilder(Material.NETHER_STAR)
                        .setDisplayName("§a Chọn Lớp")
                        .addLore("§7 Xem Và Chọn Lớp Dungeon!")
                        .addLore("")
                        .addLore("§a Lớp Bạn Đã Chọn:§b " + Level.getPlayerClass(player))
                        .addLore("")
                        .addLore("§e Bấm Để Mở Tùy Chọn!")
                        .build());
                inventory.setItem(50, new ItemBuilder(Material.SIGN)
                        .setDisplayName("§a Hồ Sơ Của Bạn! §8(§cCOMING§8)")
                        .build());
                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onCLick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().contains("Cổng Dungeons!")) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cTắt!")) {
                event.setCancelled(true);
                player.closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a Chọn Lớp")) {
                ClassOptionGui.open(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a")) {
                event.setCancelled(true);
            }
            if  (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a Tầng §7- §a1")) {
                event.setCancelled(true);
                QueueGUI q = new QueueGUI(player);
                q.open();
            }
        }
    }
}
