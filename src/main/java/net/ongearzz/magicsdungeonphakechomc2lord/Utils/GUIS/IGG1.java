package net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem.Level;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem.Level2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem.LevelConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ActiveWorld;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.OrbSystem.DungeonOrbStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IGG1 implements Listener {
    public static ItemStack Legacy1(String clazz, String clazz2, Material material, Player player, DungeonManager manager, Inventory inventory) {
        List<String> lore1 = new ArrayList<>();
        lore1.add("");
        lore1.add("§7Level: §6" + Level.getLvl(player, clazz));
        lore1.add("");
        lore1.add("§7 Những Người Đã Chọn Lớp Này: ");
        for (int i = 0; i < manager.players.size(); i++) {
            Player p = manager.players.get(i);
            if (Level2.getClass(p, manager).equals(clazz)) {
                lore1.add("§f- " + p.getName());
            }
        }
        ItemStack stack = new ItemBuilder(material)
                .setDisplayName("§a" + clazz2)
                .setLore(lore1)
                .build();
        return stack;
    }

    public static void getOrb(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 54, "Get DungeonOrb!");
        {

            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setDisplayName("§a")
                        .build());
            }

            ItemStack stack = DungeonOrbStack.getStack();
            assert stack != null;
            stack.getItemMeta().getLore().add("");
            stack.getItemMeta().getLore().add("§eClick Để Lấy DungeonOrb MIỄN PHÍ!");

            inventory.setItem(22, stack);

            player.openInventory(inventory);
        }
    }

    public static void open(Player player, DungeonManager manager) {
        if (!player.getInventory().contains(DungeonOrbStack.getStack())) {
            getOrb(player);
        } else {
            manager.needRefIGG1.add(player);
            Inventory inventory = Bukkit.createInventory(null, 54, "Ready To Start?");
            {
                for (int i = 0; i < 54; i++) {
                    inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE)
                            .setDisplayName("§a")
                            .build());
                }
                inventory.setItem(11, Legacy1("SatThu", "Sát Thủ", Material.IRON_SWORD, player, manager, inventory));
                inventory.setItem(12, Legacy1("DoDon", "Đỡ Đòn", Material.SHIELD, player, manager, inventory));
                inventory.setItem(13, Legacy1("PhapSu", "Pháp Sư", Material.BLAZE_ROD, player, manager, inventory));
                inventory.setItem(14, Legacy1("XaThu", "Xạ Thủ", Material.BOW, player, manager, inventory));
                inventory.setItem(15, Legacy1("HoTro", "Hỗ Trợ", Material.SPLASH_POTION, player, manager, inventory));
                inventory.setItem(49, new ItemBuilder(Material.BARRIER).setDisplayName("§cClose!").addLore("§e§oClick Để Tắt Inventory!").build());
                inventory.setItem(45, new ItemBuilder(Material.STAINED_GLASS_PANE)
                        .setDisplayName(manager.getId())
                        .addLore("§7Plugin này đc làm bởi oNGearZz;(ng10th4n2022)")
                        .addLore("§7tặng cho MC2LORD!")
                        .addLore("§7oNGearZz Discord: oNGearZz#7118")
                        .build());
                player.openInventory(inventory);
            }
        }
    }

    @EventHandler
    public void onclose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        if (event.getView().getTitle().equals("Ready To Start?")) {
            PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
            try {
                info2.getManager().needRefIGG1.remove(player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @EventHandler
    public void onclick(InventoryClickEvent event) {
        Player clicker = (Player) event.getWhoClicked();
        try {
            if (event.getView().getTitle().equals("Ready To Start?")) {
                if (ActiveWorld.getGetActiveWorld().contains(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Dungeons/" + event.getInventory().getItem(45).getItemMeta().getDisplayName())) {
                    event.setCancelled(true);
                    String Did = event.getInventory().getItem(45).getItemMeta().getDisplayName();
                    File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Dungeons/" + Did, "Dungeon.yml");
                    DungeonManager manager = MagicsDungeonPhakeChoMC2LORD.getInstance().managerList.get(Did);
                    PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(clicker);
                    Player player = info2.getPlayer();
                    FileConfiguration configuration = info2.getManager().getConfig();
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aSát Thủ")) {
                        if (!event.getCurrentItem().getItemMeta().getLore().contains(clicker.getName())) {
                            configuration.set("Class." + player.getName() + ".class", "SatThu");
                            Level.setClass(player, "SatThu");
                            open(player, manager);
                            configuration.save(file);
                        } else {
                            player.sendMessage("§cBạn Đã Chọn Lớp Này!");
                        }
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aĐỡ Đòn")) {
                        if (!event.getCurrentItem().getItemMeta().getLore().contains(clicker.getName())) {
                            configuration.set("Class." + player.getName() + ".class", "DoDon");
                            Level.setClass(player, "DoDon");
                            open(player, manager);
                            configuration.save(file);
                        } else {
                            player.sendMessage("§cBạn Đã Chọn Lớp Này!");
                        }
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aPháp Sư")) {
                        if (!event.getCurrentItem().getItemMeta().getLore().contains(clicker.getName())) {
                            configuration.set("Class." + player.getName() + ".class", "PhapSu");
                            Level.setClass(player, "PhapSu");
                            open(player, manager);
                            configuration.save(file);
                        } else {
                            player.sendMessage("§cBạn Đã Chọn Lớp Này!");
                        }
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aXạ Thủ")) {
                        if (!event.getCurrentItem().getItemMeta().getLore().contains(clicker.getName())) {
                            configuration.set("Class." + player.getName() + ".class", "XaThu");
                            Level.setClass(player, "XaThu");
                            open(player, manager);
                            configuration.save(file);
                        } else {
                            player.sendMessage("§cBạn Đã Chọn Lớp Này!");
                        }
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aHỗ Trợ")) {
                        if (!event.getCurrentItem().getItemMeta().getLore().contains(clicker.getName())) {
                            configuration.set("Class." + player.getName() + ".class", "HoTro");
                            Level.setClass(player, "HoTro");
                            open(player, manager);
                            configuration.save(file);
                        } else {
                            player.sendMessage("§cBạn Đã Chọn Lớp Này!");
                        }
                    }
                    if (info2.getManager().needRefIGG1.contains(player)) {
                        open(player, manager);
                    }
                    if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cClose!")) {
                        player.closeInventory();
                    }
                    info2.getManager().saveFile();
                }
            } else if (event.getView().getTitle().equals("Get DungeonOrb!")) {
                Player player = (Player) event.getWhoClicked();
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§a")) {
                    event.setCancelled(true);
                }
                assert DungeonOrbStack.getStack() != null;
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals(DungeonOrbStack.getStack().getItemMeta().getDisplayName())) {
                    player.closeInventory();
                    player.getInventory().addItem(DungeonOrbStack.getStack());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}










