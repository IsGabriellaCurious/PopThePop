package moe.gabriella.popthepop.animators.time;

import moe.gabriella.popthepop.PopThePop;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeAnimator extends BukkitRunnable {

    Player player;

    public TimeAnimator(Player player) {
        this.player = player;
        player.getWorld().setFullTime(22000);
    }

    @Override
    public void run() {
        if (player == null || !player.isOnline()) {
            cancel();
            return;
        }

        if (PopThePop.getInstance().progressMoon.contains(player)) {
            new SecondaryTimeAnimator(player, PopThePop.getInstance()).runTaskAsynchronously(PopThePop.getInstance());
            cancel();
            return;
        }

        player.getWorld().setFullTime(player.getWorld().getFullTime() + 24000);
    }
}
