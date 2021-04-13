package moe.gabriella.popthepop.animators.time;

import moe.gabriella.popthepop.PopThePop;
import moe.gabriella.popthepop.animators.BaseTitleAnimator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SecondaryTimeAnimator extends BaseTitleAnimator {

    public SecondaryTimeAnimator(Player player, JavaPlugin instance) {
        super(player, instance);
    }

    @Override
    public void run() {
        if (player == null || !player.isOnline() || !PopThePop.getInstance().progressMoon.contains(player)) {
            cancel();
            return;
        }
        Bukkit.getServer().getScheduler().runTask(PopThePop.getInstance(), () -> {
            World world = player.getWorld();
            long time = world.getFullTime();
            world.setFullTime((time + 50) + 24000);
        });
    }
}
