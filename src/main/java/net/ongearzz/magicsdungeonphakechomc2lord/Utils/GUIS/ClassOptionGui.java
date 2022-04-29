package net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem.Level;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogRecord;

public class ClassOptionGui implements Listener {
    private static void itemX(String clazz, String clazz2, Material material, Player player, Inventory inventory, int i) {
        List<String> lore = new ArrayList<>();
        if (MagicsDungeonPhakeChoMC2LORD.getInstance().getConfig().contains(clazz2 + "Lord")) {
            lore.addAll(MagicsDungeonPhakeChoMC2LORD.getInstance().getConfig().getStringList(clazz2 + "Lord"));
        }
        if (Level.getPlayerClass(player).equals(clazz2)) {
            lore.add("§aĐã Chọn!");
        } else {
            lore.add("§eBấm Vào Để Chọn!");
        }
        ItemStack itemX = new ItemBuilder(material).setDisplayName(clazz).setLore(lore).build();
        inventory.setItem(i, itemX);
    }

    public static void open(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "Tùy Chọn Lớp Cho: " + player.getName());
        {
            PlayerInfo2 playerInfo = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
            if (Level.getPlayerClass(player).equals("SatThu")) {
            }
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE)
                        .setDisplayName("§a")
                        .build());
            }
            inventory.setItem(21, new ItemBuilder(Material.ARROW).setDisplayName("§aTrở Lại Menu Chính!")
                    .build());
            inventory.setItem(22, new ItemBuilder(Material.BARRIER).setDisplayName("§cTắt!")
                    .build());
            itemX("§aSát Thủ", "SatThu", Material.IRON_SWORD, player, inventory, 11);
            itemX("§aĐở Đòn", "DoDon", Material.SHIELD, player, inventory, 12);
            itemX("§aPháp Sư", "PhapSu", Material.BLAZE_ROD, player, inventory, 13);
            itemX("§aXạ Thủ", "XaThu", Material.BOW, player, inventory, 14);
            itemX("§aHỗ Trợ", "HoTro", Material.SPLASH_POTION, player, inventory, 15);
        }
        player.openInventory(inventory);
    }

    @EventHandler
    public void onclick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().contains("Tùy Chọn Lớp Cho: ")) {
            String playerName = event.getView().getTitle().split("Tùy Chọn Lớp Cho: ")[1];
            PlayerInfo2 playerInfo = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cTắt!")) {
                event.setCancelled(true);
                player.closeInventory();
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aTrở Lại Menu Chính!")) {
                MainGUI.open(player);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a")) {
                event.setCancelled(true);
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aHỗ Trợ")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getLore().contains("§aĐã Chọn!")) {
                    player.sendMessage("§cBạn Đã Chọn Lớp Này Rồi!");
                    open(player);
                } else if (event.getCurrentItem().getItemMeta().getLore().contains("§eBấm Vào Để Chọn!")) {
                    Level.setClass(player, "HoTro");
                    player.sendMessage("§a Chọn Thành Công!");
                    open(player);
                }
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aXạ Thủ")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getLore().contains("§aĐã Chọn!")) {
                    player.sendMessage("§cBạn Đã Chọn Lớp Này Rồi!");
                    open(player);
                } else if (event.getCurrentItem().getItemMeta().getLore().contains("§eBấm Vào Để Chọn!")) {
                    Level.setClass(player, "XaThu");
                    player.sendMessage("§a Chọn Thành Công!");
                    open(player);
                }
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aPháp Sư")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getLore().contains("§aĐã Chọn!")) {
                    player.sendMessage("§cBạn Đã Chọn Lớp Này Rồi!");
                    open(player);
                } else if (event.getCurrentItem().getItemMeta().getLore().contains("§eBấm Vào Để Chọn!")) {
                    Level.setClass(player, "PhapSu");
                    player.sendMessage("§a Chọn Thành Công!");
                    open(player);
                }
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aĐở Đòn")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getLore().contains("§aĐã Chọn!")) {
                    player.sendMessage("§cBạn Đã Chọn Lớp Này Rồi!");
                    open(player);
                } else if (event.getCurrentItem().getItemMeta().getLore().contains("§eBấm Vào Để Chọn!")) {
                    Level.setClass(player, "DoDon");
                    player.sendMessage("§a Chọn Thành Công!");
                    open(player);
                }
            }
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aSát Thủ")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getLore().contains("§aĐã Chọn!")) {
                    player.sendMessage("§cBạn Đã Chọn Lớp Này Rồi!");
                    open(player);
                } else if (event.getCurrentItem().getItemMeta().getLore().contains("§eBấm Vào Để Chọn!")) {
                    Level.setClass(player, "SatThu");
                    player.sendMessage("§a Chọn Thành Công!");
                    open(player);
                }
            }
        }
    }
}
