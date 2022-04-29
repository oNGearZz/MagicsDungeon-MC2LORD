package net.ongearzz.magicsdungeonphakechomc2lord.Commands;


import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class testcm implements CommandExecutor, Listener {
    private WorldManager worldManager;
    private DungeonManager dungeonManager;
    private Player player;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
//        Map<String, Player> test = new HashMap<>();
//        test.put(player.getName(), player);
//        WorldManager manager = new WorldManager();
//        manager.StartGenerator("world");
//        DungeonManager manager1 = new DungeonManager(manager.id, test, manager);
//        //manager1.ListPlayer.add(player);
//        manager1.waitToStart(player);
//        manager1.load();
        WorldManager worldManager = new WorldManager();
        worldManager.StartGenerator("DungeonWorld1");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
      //  players.add(Bukkit.getPlayer("ThanhMagicsVN"));
        DungeonManager dungeonManager = new DungeonManager(worldManager.id, players, worldManager, "DungeonWorld1");
        dungeonManager.load();
        dungeonManager.waitToStart();
//        DungeonMangerTest dungeonManger = new DungeonMangerTest(worldManager.id, players, worldM;anager);
//        dungeonManger.load();
//        dungeonManger.waitToStart(player);
        this.worldManager = worldManager;
        this.player = player;

        return true;
    }

//    @EventHandler
//    public void oncreate(NPCSpawnEventPlus event) {
//        if (ArrayListStorge.getInstance().getActiveWorld().contains(worldManager.id)) {
//            for (int i = 0; i < ArrayListStorge.getInstance().getActiveWorld().size(); i++) {
//                if (event.getNPC().getEntity().getLocation().getWorld().getName().equals(ArrayListStorge.getInstance().getActiveWorld().get(i))) {
//                    event.createSNpc(true);
//                    event.createActive(worldManager.id);
//                }
//            }
//        }
//    }
//
//    @EventHandler
//    public void onclick(NPCClickEvent event) {
//        String npcWorldName = event.getNPC().getEntity().getWorld().getName();
//        String npcName = event.getNPC().getName();
//        if (HashMapStorge.getInstance().getNpc().containsKey(npcName)) {
//            if (HashMapStorge.getInstance().getNpc().get(npcName).equals(npcWorldName)) {
//
//            }
//        }
//    }
}
