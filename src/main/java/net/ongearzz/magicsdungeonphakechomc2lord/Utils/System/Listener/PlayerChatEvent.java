package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.LootChestConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.MapsConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupGui;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupInv;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Reward.RewardConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Reward.RewardGUISetup;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerChatEvent implements Listener {
    public int plsnum(String s) {
        return Integer.parseInt(s);
    }

    public static Map<Player, String> playerEdit = new HashMap<>();
    public static Map<Player, String> playerEdit2 = new HashMap<>();

    @EventHandler
    public void onchat(org.bukkit.event.player.PlayerChatEvent event) {
        String msg = event.getMessage();
        Player player = event.getPlayer();
        if (msg.equals("cancel")) {
            if (BlockBreakEvt.edit.containsKey(player.getUniqueId())) {
                event.setCancelled(true);
                BlockBreakEvt.edit.remove(player.getUniqueId());
                player.sendMessage("Thoát Thành Công!");
            } else if (playerEdit.containsKey(player)) {
                event.setCancelled(true);
                playerEdit.remove(player);
                player.sendMessage("Thoát Thành Công!");
            } else if (BlockPlaceEvent.edit2.containsKey(player.getUniqueId())) {
                event.setCancelled(true);
                SetupInv.remove(player);
                player.sendMessage("Thoát Thành Công!");
            } else if (BlockBreakEvt.edit2.containsKey(player.getUniqueId())) {
                event.setCancelled(true);
                BlockBreakEvt.edit2.remove(player.getUniqueId());
                player.sendMessage("Thoát Thành Công");
            } else if (BlockBreakEvt.edit3.containsKey(player.getUniqueId())) {
                event.setCancelled(true);
                BlockBreakEvt.edit3.remove(player.getUniqueId());
                player.sendMessage("Thoát Thành Công");
            } else if (BlockBreakEvt.edit4.containsKey(player.getUniqueId())) {
                event.setCancelled(true);
                BlockBreakEvt.edit4.remove(player.getUniqueId());
                player.sendMessage("Thoat Thanh Cong!");
            } else if (BlockBreakEvt.edit5.containsKey(player.getUniqueId())) {
                event.setCancelled(true);
                BlockBreakEvt.edit5.remove(player.getUniqueId());
                player.sendMessage("Thoat Thanh Cong!");
            } else if (playerEdit2.containsKey(player)) {
                event.setCancelled(true);
                playerEdit2.remove(player);
                player.sendMessage("Thoat Thanh Cong!");
            }
        }
        if (playerEdit.containsKey(player)) {
            if (playerEdit.get(player).split("-")[0].equals("LootChest")) {
                if (playerEdit.get(player).split("-")[1].equals("chance")) {
                    event.setCancelled(true);
                    String num = playerEdit.get(player).split("-")[2];
                    try {
                        int i = plsnum(msg);
                        LootChestConfig.getConfiguration().set("Items." + num + ".chance", i);
                        LootChestConfig.save();
                        player.sendMessage(ChatColor.GREEN + "Add Thành Công!");
                        playerEdit.remove(player);
                        SetupGui.customitem(player);
                    } catch (Exception e) {
                        player.sendMessage("pls ghi số!");
                        playerEdit.remove(player);
                        SetupGui.customitem(player);
                    }
                }
            }
        } else if (playerEdit2.containsKey(player)) {
            if (playerEdit2.get(player).split("-")[0].equals("reward")) {
                if (playerEdit2.get(player).split("-")[1].equals("chance")) {
                    event.setCancelled(true);
                    String number = playerEdit2.get(player).split("-")[2];
                    String score = playerEdit2.get(player).split("-")[3];
                    try {
                        int i = plsnum(msg);
                        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder(), "Reward.yml");
                        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
                        try {
                            configuration.set(score +  ".Items." + number + ".chance", i);
                            configuration.save(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.sendMessage("Add Thanh Cong!");
                        playerEdit2.remove(player);
                        RewardGUISetup.open(player,score);
                    } catch (Exception e) {
                        player.sendMessage("pls ghi so!");
                        playerEdit2.remove(player);
                        RewardGUISetup.open(player,score);
                    }
                }
            }
        }
    }
}
