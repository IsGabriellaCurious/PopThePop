package moe.gabriella.popthepop;

import moe.gabriella.popthepop.utils.ActionBar;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

public class EventManager implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);
        ActionBar.sendActionbar(event.getPlayer(), "" + ChatColor.RED + "You cannot chat right now!");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        PopThePop.getInstance().beginAnimation(event.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
        PopThePop.getInstance().deleteWorld(event.getPlayer(), false);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (!PopThePop.getInstance().canMove(event.getPlayer()))
            event.setTo(event.getFrom());
    }

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent event) {
        if (!PopThePop.getInstance().canMove(event.getPlayer()))
            event.getPlayer().setFlying(true);
    }

}
