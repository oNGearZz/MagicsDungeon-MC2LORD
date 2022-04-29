package net.ongearzz.magicsdungeonphakechomc2lord.Utils;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.Party2;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem.PartyDungeon;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.Tab.TAB;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PlayerInfo2 {
    private Player player;
    private String playerName;
    private boolean ig;
    private DungeonManager manager;
    private PlayerData data;
    private Party2 party;
    private TAB tab;
    private boolean b;

    public PlayerInfo2(Player player) {
        this.player = player;
        this.playerName = player.getName();
        this.ig = false;
        this.manager = null;
        MagicsDungeonPhakeChoMC2LORD.getInstance().playerInfo2.put(player, this);
        PlayerData data = new PlayerData(player);
        MagicsDungeonPhakeChoMC2LORD.getInstance().playerPlayerDataMap.put(player, data);
        this.data = data;
    }


    public void setParty(Party2 party) {
        this.party = party;
    }

    public void setTab(TAB tab) {
        this.tab = tab;
    }

    public TAB getTab() {
        return tab;
    }

    public Party2 getParty() {
        return party;
    }
    public boolean isB() {
        return b;
    }


    public void setB(boolean b) {
        this.b = b;
    }

    public void setData(PlayerData data) {
        this.data = data;
    }

    public void setManager(DungeonManager manager) {
        this.manager = manager;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, playerName, ig, manager, data, party, tab, b);
    }

    public PlayerData getData() {
        return data;
    }

    public void SetManager(DungeonManager dungeonManager) {
        this.manager = dungeonManager;
    }

    public DungeonManager getManager() {
        return manager;
    }

    public boolean isIg() {
        return ig;
    }

    public void setIg(boolean start) {
        this.ig = start;
    }

    public Player getPlayer() {
        return player;
    }

    public String getPlayerName() {
        return playerName;
    }
}
