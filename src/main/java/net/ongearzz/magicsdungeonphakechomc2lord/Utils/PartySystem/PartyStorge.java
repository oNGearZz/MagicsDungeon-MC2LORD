package net.ongearzz.magicsdungeonphakechomc2lord.Utils.PartySystem;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartyStorge {
    private static Map<Player, String> s1 = new HashMap<>();
    private static Map<String, List<String>> s2 = new HashMap<>();

    public static Map<Player, String> getS1() {
        return s1;
    }

    public static Map<String, List<String>> getS2() {
        return s2;
    }

    private static Map<Integer,PartyDungeon> getS3 = new HashMap<>();

    public static Map<Integer, PartyDungeon> getGetS3() {
        return getS3;
    }
}
