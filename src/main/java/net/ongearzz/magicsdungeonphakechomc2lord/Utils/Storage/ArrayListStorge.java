package net.ongearzz.magicsdungeonphakechomc2lord.Utils.Storage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayListStorge {
    private static ArrayListStorge ArrayListStorge;

    public static ArrayListStorge getInstance() {
        return ArrayListStorge;
    }

    public List<String> getL2() {
        return L2;
    }

    public List<String> getActiveWorld() {
        return ActiveWorld;
    }

    List<String> ActiveWorld = new ArrayList<>();
    List<String> L2 = new ArrayList<>();
    List<String> L1 = new ArrayList<>();
}
