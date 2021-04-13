package moe.gabriella.popthepop.animators;

import moe.gabriella.popthepop.PopThePop;
import moe.gabriella.popthepop.utils.ActionBar;
import moe.gabriella.popthepop.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.TimeUnit;

public abstract class BaseTitleAnimator extends BukkitRunnable {

    public Player player;
    public JavaPlugin instance;

    public BaseTitleAnimator(Player player, JavaPlugin instance) {
        this.player = player;
        this.instance = instance;
    }


    public void message(String top, String bottom, int in, int stay, int out, int secs, boolean chatTitle) throws InterruptedException {
        player.sendTitle(top, bottom, in, stay, out);
        Message.debug(top + " " + ChatColor.RESET + bottom);
        if (!chatTitle && PopThePop.emergencyChatMode) {
            if ((top != null && !top.equals("")) && (bottom != null && !bottom.equals(""))) {
                player.sendMessage(top + " " + ChatColor.RESET + bottom);
            } else {
                if (top != null && !top.equals(""))
                    player.sendMessage(top);
                if (bottom != null && !bottom.equals(""))
                    player.sendMessage(bottom);
            }
        } else if (!chatTitle) {
            ActionBar.sendActionbar(player, top + " " + ChatColor.RESET + bottom);
        }
        TimeUnit.SECONDS.sleep(secs);
    }

    public void chatTitle(String top, String bottom, int in, int stay, int out, int secs, boolean showTop, boolean showBottom) throws InterruptedException {
        if (showTop && showBottom) {
            player.sendMessage(top + " " + ChatColor.RESET + bottom);
        } else {
            if (showTop)
                player.sendMessage(top);
            if (showBottom)
                player.sendMessage(bottom);
        }
        message(top, bottom, in, stay, out, secs, true);
    }

    public void flash(String text, String sub, ChatColor primary, ChatColor secondary) throws InterruptedException {
        int curr = 0;
        Message.debug("Flashing " + text);
        while (curr != text.length()) {
            StringBuilder out = new StringBuilder(primary + "" + ChatColor.BOLD);

            for (int i=0; i<text.length(); i++) {
                char c = text.charAt(i);
                if (i == curr)
                    out.append(secondary).append(ChatColor.BOLD);
                if (i == curr + 1)
                    out.append(ChatColor.WHITE + "" + ChatColor.BOLD);

                out.append(c).append(primary).append(ChatColor.BOLD);
            }

            curr++;

            if (curr == text.length()) {
                out = new StringBuilder(primary + "" + ChatColor.BOLD + text);
            }

            player.sendTitle(out.toString(), sub, 0, 60, (curr == text.length() ? 20 : 0));
            TimeUnit.MILLISECONDS.sleep(25);
        }
    }

    public void wait(int seconds) throws InterruptedException {
        Message.debug("Waiting " + seconds + "s.");
        TimeUnit.SECONDS.sleep(seconds);
    }

}
