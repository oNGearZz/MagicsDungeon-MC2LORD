package net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.ScoreBoards;

import net.ongearzz.magicsdungeonphakechomc2lord.MagicsDungeonPhakeChoMC2LORD;
import net.ongearzz.magicsdungeonphakechomc2lord.Utils.System.DungeonManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class CreateBoards {
    private Scoreboard scoreboard;
    private Objective objective;

    public void create(DungeonManager manager) {
        new BukkitRunnable() {
            @Override
            public void run() {
                ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
                Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
                Objective objective = scoreboard.registerNewObjective("MC2LORD.NET", "dummy");
                objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                Score score = objective.getScore("");
                score.setScore(0);
                Score score1 = objective.getScore("§aThời Gian: " + manager.getTime());
                score1.setScore(0);
                for (int i = 0; i < manager.players.size(); i++) {
                    Player player = manager.players.get(i);
                    int phel = (int) player.getHealth();
                    if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                        Score score3 = objective.getScore("§e[" + manager.getClassBieuTuong(player) + "] §a" + player.getName() + " §c" + phel + "❤");
                        score3.setScore((0));
                    } else {
                        Score score3 = objective.getScore("§e[" + manager.getClassBieuTuong(player) + "] §c" + player.getName() + " §c" + 0 + "❤");
                        score3.setScore((0));
                    }
                }

                for (int i = 0; i < manager.players.size(); i++) {
                    Player player = manager.players.get(i);
                    player.setScoreboard(scoreboard);
                }
            }
        }.runTaskTimer(MagicsDungeonPhakeChoMC2LORD.getInstance(), 0, 20);
    }
}
