package net.ongearzz.magicsdungeonphakechomc2lord.Utils.Ability.List;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.Ability.Ability;

public class test implements Ability {
    @Override
    public void load() {
        MagicsDungeonPhakeChoMC2LORD.getInstance().abilitys.add("test");
    }

    @Override
    public void start() {

    }
}
