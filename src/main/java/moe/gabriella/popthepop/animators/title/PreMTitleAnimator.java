package moe.gabriella.popthepop.animators.title;

import moe.gabriella.popthepop.PopThePop;
import moe.gabriella.popthepop.animators.BaseTitleAnimator;
import moe.gabriella.popthepop.guis.MessagesGui;
import moe.gabriella.popthepop.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public class PreMTitleAnimator extends BaseTitleAnimator {

    ChatColor primaryRM = ChatColor.GOLD;
    ChatColor secondaryRM = ChatColor.YELLOW;

    public PreMTitleAnimator(Player player, JavaPlugin instance) {
        super(player, instance);
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(4);

            message("" + ChatColor.YELLOW + "Hey!! Welcome to", Message.formatBare(false), 10, 100, 10, 4, false);
            message("", "It's the start of a very epic month!", 10, 100, 10, 4, false);

            message("" + primaryRM + ChatColor.BOLD + "Ramadan Mubarak!", "Wishing you a blessed Ramadan", 10, 80, 10, 3, false);
            flash("Ramadan Mubarak!", "Wishing you a blessed Ramadan", primaryRM, secondaryRM);
            wait(2);

            message(ChatColor.GREEN + "So as a present,", "we made you this!", 10, 100, 10, 4, false);
            message(ChatColor.LIGHT_PURPLE + "Something you might like:", "We have written some nice messages for you!", 10, 100, 10, 4, false);
            message(ChatColor.LIGHT_PURPLE + "These include", "some wise words", 10, 100, 10, 4, false);
            message("", "from all the Lads and Lasses!", 10, 100, 10, 4, false);
            message("", "Would you like to see them?", 10, 100, 10, 4, false);

            Bukkit.getServer().getScheduler().runTask(instance, () -> {
               new MessagesGui(instance, player).open(false);
            });

            cancel();
        } catch (Exception e) {
            //Message.warn("Error in " + player.getName() + "'s animator: " + e.getLocalizedMessage() + ". Killing world.");
            //player.kickPlayer(Message.format("Error during animation. Please retry! " + e.getLocalizedMessage()));
            //instance.deleteWorld(player, false);
            e.printStackTrace();
            player.sendMessage(Message.format("There was an error running titles. ffs gabi your coding is shit"));
            cancel();
        }
    }
}
