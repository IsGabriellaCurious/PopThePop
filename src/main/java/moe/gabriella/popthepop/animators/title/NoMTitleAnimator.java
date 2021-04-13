package moe.gabriella.popthepop.animators.title;

import moe.gabriella.popthepop.PopThePop;
import moe.gabriella.popthepop.animators.BaseTitleAnimator;
import moe.gabriella.popthepop.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NoMTitleAnimator extends BaseTitleAnimator {

    public NoMTitleAnimator(Player player, JavaPlugin instance) {
        super(player, instance);
    }

    @Override
    public void run() {
        try {
            message("" + ChatColor.RED + "Welp, sorry everyone who", "wrote a message...", 10, 100, 10, 4, false);

            Bukkit.getServer().getScheduler().runTask(PopThePop.getInstance(), () -> PopThePop.getInstance().deleteWorld(player, true));
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(Message.format("There was an error running titles. ffs gabi your coding is shit"));
            cancel();
        }
    }
}
