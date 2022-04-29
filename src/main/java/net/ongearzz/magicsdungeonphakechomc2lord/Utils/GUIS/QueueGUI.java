package net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.ItemBuilder;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.PartyDungeon;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PlayerInfo2;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class QueueGUI {

    private Player player;

    public QueueGUI(Player player) {
        this.player = player;
    }

    private List<Integer> freeSlot = new ArrayList<>();

    public void open() {
        PlayerInfo2 info = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
        Inventory inventory = Bukkit.createInventory(null, 54, "Tìm Party!");
        {

            for (int a = 0; a < 9; a++) {
                inventory.setItem(a, new ItemBuilder(Material.STAINED_GLASS_PANE)
                        .setDisplayName("§a").build());
            }

            for (int b = 45; b < inventory.getSize(); b++) {
                inventory.setItem(b, new ItemBuilder(Material.STAINED_GLASS_PANE)
                        .setDisplayName("§a").build());
            }

            inventory.setItem(9, new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setDisplayName("§a").build());
            inventory.setItem(18, new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setDisplayName("§a").build());
            inventory.setItem(27, new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setDisplayName("§a").build());
            inventory.setItem(36, new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setDisplayName("§a").build());

            inventory.setItem(17, new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setDisplayName("§a").build());
            inventory.setItem(26, new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setDisplayName("§a").build());
            inventory.setItem(35, new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setDisplayName("§a").build());
            inventory.setItem(44, new ItemBuilder(Material.STAINED_GLASS_PANE)
                    .setDisplayName("§a").build());

            for (int i = 0; i < inventory.getSize(); i++) {
                if (inventory.getItem(i) != null) {
                    freeSlot.add(i);
                }
            }
//
            for (int i = 0; i < freeSlot.size(); i++) {
                try {
                    if (MagicsDungeonPhakeChoMC2LORD.getInstance().inQueue.size() > 0) {
                        if (MagicsDungeonPhakeChoMC2LORD.getInstance().inQueue.get(i).getParty() != null) {
                            PartyDungeon party = MagicsDungeonPhakeChoMC2LORD.getInstance().inQueue.get(i).getParty();
                            List<String> lore = new ArrayList<>();
                            lore.add("");
                            for (int j = 0; j < party.getPlayers().size(); j++) {
                                Player player = Bukkit.getPlayer(party.getPlayers().get(j));
                                PlayerInfo2 info2 = MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.get(player);
                                lore.add("§f- §a" + player.getName());
                            }
                            lore.add("");
                            lore.add("§e Bấm Vào Để Tham Gia!");
                            inventory.setItem(i, new ItemBuilder(ItemBuilder.SkullWithValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDI4NDNjM2MyMzU3MTZmM2ViNWNkOWMzYmRiZjIwODUzZjUwYTY1ZGMyMjMwNThiMWUxZWVmZmRlOTlmNjExMCJ9fX0="))
                                    .setDisplayName("§a" + party.leader)
                                    .setLore(lore)
                                    .build());
                            //indexOutOfBoundsException
                        }
                    }
                } catch(IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    player.sendMessage(":a");
                }
            }
            player.openInventory(inventory);
        }
    }
}
