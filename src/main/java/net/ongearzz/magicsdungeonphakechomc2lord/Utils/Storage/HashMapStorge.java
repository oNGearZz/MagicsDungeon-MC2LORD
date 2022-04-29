package net.ongearzz.magicsdungeonphakechomc2lord.Utils.Storage;

import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.Party;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorge {
    private static HashMapStorge hashMapStorge;

    public static HashMapStorge getInstance() {
        return hashMapStorge;
    }

    public Map<String, String> getGetMobID() {
        return getMobID;
    }


    public Map<String, YamlConfiguration> getPlayerDataConfig() {
        return PlayerDataConfig;
    }

    public Map<String, Party> getParty() {
        return party;
    }

    public Map<String, String> getNpc() {
        return npc;
    }

    Map<String, String> npc = new HashMap<>();
    Map<String, Party> party = new HashMap<>();
    Map<String, YamlConfiguration> PlayerDataConfig = new HashMap<>();
    Map<String, String> getMobID = new HashMap<>();

    public Map<String, String> getDulieuDungeon() {
        return dulieuDungeon;
    }

    Map<String, String> dulieuDungeon = new HashMap<>();
}
