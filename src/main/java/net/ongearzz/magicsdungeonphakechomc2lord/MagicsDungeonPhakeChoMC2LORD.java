package net.ongearzz.magicsdungeonphakechomc2lord;

import net.ongearzz.magicsdungeonphakechomc2lord.Commands.*;
import net.ongearzz.magicsdungeonphakechomc2lord.Commands.Setup.SetupCommands;
import net.ongearzz.magicsdungeonphakechomc2lord.Map1.Manager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.*;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Class.LevelSystem.LevelConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS.*;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.GUIS.Queue;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Npc.NpcCreate;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.Party2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.PartyDungeon;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.PartyFile;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Storage.HashMapStorge;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ActiveWorld;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Events.ServerShutDownEvent;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Listener.*;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.LootChestSystem.SetupInv;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Reward.RewardConfig;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Reward.RewardGUISetup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public final class MagicsDungeonPhakeChoMC2LORD extends JavaPlugin {

    private static MagicsDungeonPhakeChoMC2LORD phakeChoMC2LORD;
    public Map<Player, PlayerInfo2> playerInfo2 = new HashMap<>();
    public Map<String, DungeonManager> managerList = new HashMap<>();
    public Map<String, NpcCreate> createMap = new HashMap<>();
    public Map<String, MapsConfig> mapsConfig = new HashMap<>();
    public Map<Player, PlayerData> playerPlayerDataMap = new HashMap<>();
    public List<String> worlds = new ArrayList<>();
    public Map<String, String> dulieuDungeon = new HashMap<>();
    public List<String> abilitys = new ArrayList<>();
    public List<Queue> inQueue = new ArrayList<>();

    public Map<PartyDungeon , Queue> queues = new HashMap<>();
    public Map<Player, String> getS1 = new HashMap<>();
    public Map<Integer,PartyDungeon> getS3 = new HashMap<>();
    public Map<Player, PartyDungeon> partys = new HashMap<>();

    public Map<UUID, Party2> partyMap = new HashMap<>();

    public RewardConfig rewardConfig;

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveConfig();
        saveDefaultConfig();
        phakeChoMC2LORD = this;
        createFolder();
        loadplayerdata();
        LevelConfig.load();
        addOnlinePlayers();
        MapsConfig mapsConfig = new MapsConfig("DungeonWorld1");
        worlds.add("DungeonWorld1");
        mapsConfig.load();
        Lang.load();

        LootChestConfig.load();

        Checker.start();
        MapsConfig.loadParticle1(mapsConfig);
        loadConfig();
        loadconfig2();
        ActiveWorld.load();
        SetupInv.removeALL();
        RewardConfig rewardConfig = new RewardConfig();
        rewardConfig.load();
        this.rewardConfig = rewardConfig;

        getServer().getPluginManager().registerEvents(new PJoinEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockBreakEvt(), this);
        getServer().getPluginManager().registerEvents(new testcm(), this);
        getServer().getPluginManager().registerEvents(new AntiDefaultMobSpawm(), this);
        getServer().getPluginManager().registerEvents(new PQuitEvent(), this);
        getServer().getPluginManager().registerEvents(new IGG1(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceEvent(), this);
        getServer().getPluginManager().registerEvents(new InvClickEvent(), this);
        getServer().getPluginManager().registerEvents(new InvCloseEvent(), this);
        getServer().getPluginManager().registerEvents(new MainGUI(), this);
        getServer().getPluginManager().registerEvents(new ClassOptionGui(), this);
        getServer().getPluginManager().registerEvents(new LootChestOpenEvent(), this);
        getServer().getPluginManager().registerEvents(new EntityDeathEvt(), this);
        getServer().getPluginManager().registerEvents(new QueueGUI_Listener(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractatEnityty(), this);
        getServer().getPluginManager().registerEvents(new ProfileGUI(), this);
        getServer().getPluginManager().registerEvents(new RewardGUISetup(), this);

        getCommand("testDungeon").setExecutor(new testcm());
        getCommand("GetLocationWand").setExecutor(new LocationHelper());
        getCommand("DungeonEdit").setExecutor(new SetupCommands());
        getCommand("testcm2").setExecutor(new testcm2());
        getCommand("DungeonParty").setExecutor(new PartyCommand());
        getCommand("MagicsDungeon").setExecutor(new MagicsDungeon());
        getCommand("dpc").setExecutor(new PartyCommand_DPC());
    }

    public void loadConfig() {
        if (!getConfig().contains("SatThuLore")) {
            getConfig().set("SatThuLore", LevelConfig.SatThuLore);
            saveConfig();
        }
        if (!getConfig().contains("DoDonLore")) {
            getConfig().set("DoDonLore", LevelConfig.DoDonLore);
            saveConfig();
        }
        if (!getConfig().contains("PhapSuLore")) {
            getConfig().set("PhapSuLore", LevelConfig.PhapSuLore);
            saveConfig();
        }
        if (!getConfig().contains("XaThuLore")) {
            getConfig().set("XaThuLore", LevelConfig.XaThuLore);
            saveConfig();
        }
        if (!getConfig().contains("HoTroLore")) {
            getConfig().set("HoTroLore", LevelConfig.HoTroLore);
            saveConfig();
        }
        if (!getConfig().contains("PartyInviteHelp")) {
            getConfig().set("PartyInviteHelp", PartyFile.PartyInviteHelp);
            saveConfig();
        }
        if (!getConfig().contains("PartyCreateThanhCong")) {
            getConfig().set("PartyCreateThanhCong", PartyFile.PartyCreateThanhCong);
            saveConfig();
        }
        if (!getConfig().contains("PartyCreateKoThanhCong")) {
            getConfig().set("PartyCreateKoThanhCong", PartyFile.PartyCreateKoThanhCong);
            saveConfig();
        }
        if (!getConfig().contains("PartyDisbandThanhCong")) {
            getConfig().set("PartyDisbandThanhCong", PartyFile.PartyDisbandThanhCong);
            saveConfig();
        }
        if (!getConfig().contains("PartyHelp")) {
            getConfig().set("PartyHelp",new ArrayList<>());
            saveConfig();
        }
        if (!getConfig().contains("PartyCreateThanhCong")) {
            getConfig().set("PartyCreateThanhCong", new ArrayList<>());
            saveConfig();
        }
        if (!getConfig().contains("PartyCreateKoThanhCong")) {
            getConfig().set("PartyCreateKoThanhCong", new ArrayList<>());
            saveConfig();
        }
        if (!getConfig().contains("PartyDisbandThanhCong")) {
            getConfig().set("PartyDisbandThanhCong", new ArrayList<>());
            saveConfig();
        }
        if (!getConfig().contains("PartyList")) {
            getConfig().set("PartyList", new ArrayList<>());
            saveConfig();
        }
    }

    public String getNamePlugin() {
        return this.getNamePlugin();
    }

    public void loadconfig2() {
        if (getConfig().contains("SatThuLore")) {
            LevelConfig.SatThuLore.addAll(getConfig().getStringList("SatThuLore"));
            saveConfig();
        }
        if (getConfig().contains("DoDonLore")) {
            LevelConfig.DoDonLore.addAll(getConfig().getStringList("DoDonLore"));
            saveConfig();
        }
        if (getConfig().contains("PhapSuLore")) {
            LevelConfig.PhapSuLore.addAll(getConfig().getStringList("PhapSuLore"));
            saveConfig();
        }
        if (getConfig().contains("XaThuLore")) {
            LevelConfig.XaThuLore.addAll(getConfig().getStringList("XaThuLore"));
            saveConfig();
        }
        if (getConfig().contains("HoTroLore")) {
            LevelConfig.HoTroLore.addAll(getConfig().getStringList("HoTroLore"));
            saveConfig();
        }
    }

    private void addOnlinePlayers() {
        for (Player player : Bukkit.getOnlinePlayers())
            this.playerInfo2.put(player, new PlayerInfo2(player));
    }

    @Override
    public void onDisable() {
        ActiveWorld.clear();
        MapsConfig.clearStorge();
        // Plugin shutdown logic
        Bukkit.getPluginManager().callEvent((Event) new ServerShutDownEvent());
        MapsConfig mapsConfig = this.mapsConfig.get("DungeonWorld1");
        mapsConfig.saveXreload();
        rewardConfig.save();
        getConfig().set("SatThuLore", LevelConfig.SatThuLore);
        getConfig().set("DoDonLore", LevelConfig.DoDonLore);
        getConfig().set("PhapSuLore", LevelConfig.PhapSuLore);
        getConfig().set("XaThuLore", LevelConfig.XaThuLore);
        getConfig().set("HoTroLore", LevelConfig.HoTroLore);
    }

    public static MagicsDungeonPhakeChoMC2LORD getInstance() {
        return phakeChoMC2LORD;
    }

    public void loadArena() {
        String[] s1;
    }

    public void createFolder() {
        try {
            File f1 = new File(getDataFolder() + "/Dungeons");
            if (!f1.exists()) {
                Files.createDirectories(Paths.get(this.getDataFolder() + "/Dungeons/"));
                saveConfig();
            }
            File f2 = new File(getDataFolder() + "/Partys");
            if (!f2.exists()) {
                Files.createDirectories(Paths.get(this.getDataFolder() + "/Partys"));
                saveConfig();
            }
            File f3 = new File(getDataFolder() + "/Players");
            if (!f3.exists()) {
                Files.createDirectories(Paths.get(this.getDataFolder() + "/Players"));
                saveConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadplayerdata() {
        try {
            File f1 = new File(getDataFolder() + "/Players");
            if (f1.exists()) {
                String[] list;
                File file = new File(this.getDataFolder() + "/Players");
                file.getName().replace(".yml", "");
                list = file.list();
                assert list != null;
                for (String List : list) {
                    PlayerData data = new PlayerData(List);
                    HashMapStorge.getInstance().getPlayerDataConfig().put(List.toString(), data.getYamlConfig());
                }
            } else {
                createFolder();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAuthor() {
        return "oNGearZz";
    }
}
