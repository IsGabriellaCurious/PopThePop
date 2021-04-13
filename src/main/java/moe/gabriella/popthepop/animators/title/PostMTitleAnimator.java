package moe.gabriella.popthepop.animators.title;

import moe.gabriella.popthepop.PopThePop;
import moe.gabriella.popthepop.animators.BaseTitleAnimator;
import moe.gabriella.popthepop.animators.time.SecondaryTimeAnimator;
import moe.gabriella.popthepop.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class PostMTitleAnimator extends BaseTitleAnimator {

    public PostMTitleAnimator(Player player, JavaPlugin instance) {
        super(player, instance);
    }

    @Override
    public void run() {
        try {
            message(ChatColor.GOLD + "That is all the messages!", "We hope you liked them!!!", 10, 100, 10, 4, false);
            wait(2);

            message(ChatColor.GOLD + "Once again,", "wishing you an amazing Ramadan", 10, 100, 10, 3, false);
            message("", ChatColor.GOLD + "Enjoy the month!", 10 ,100, 10, 4, false);
            message(ChatColor.LIGHT_PURPLE + "Love you!", "Bye bye!", 10, 100, 10, 0, false);

            PopThePop.getInstance().toggleMovement(player, true);
            PopThePop.getInstance().progressMoon.add(player);
            player.playSound(player.getLocation(), Sound.MUSIC_DISC_PIGSTEP, 1f, 1f);
            new SecondaryTimeAnimator(player, PopThePop.getInstance()).runTaskTimer(PopThePop.getInstance(), 0, 1);
            wait(60);
            Bukkit.getServer().getScheduler().runTask(PopThePop.getInstance(), () -> PopThePop.getInstance().deleteWorld(player, true));
            cancel();
        } catch (Exception e) {
            e.printStackTrace();
            player.sendMessage(Message.format("There was an error running titles. ffs gabi your coding is shit"));
            cancel();
        }
    }
}
