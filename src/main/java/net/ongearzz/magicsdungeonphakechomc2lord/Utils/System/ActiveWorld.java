package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class ActiveWorld {
    public static ArrayList<String> getGetActiveWorld() {
        return getActiveWorld;
    }

    static ArrayList<String> getActiveWorld = new ArrayList<>();

    public static void reload() {
        getActiveWorld.clear();
        String[] s;
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Dungeons");
        s = file.list();
        assert s != null;
        getActiveWorld.addAll(Arrays.asList(s));
    }

    public static void load() {
        String[] s;
        File file = new File(MagicsDungeonPhakeChoMC2LORD.getInstance().getDataFolder() + "/Dungeons");
        s = file.list();
        assert s != null;
        getActiveWorld.addAll(Arrays.asList(s));
    }

    public static void clear() {
        getActiveWorld.clear();
    }
}
