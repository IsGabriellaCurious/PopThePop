package moe.gabriella.popthepop.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {

    public static void sendActionbar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }

    public static void broadcastActionbar(String message) {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            sendActionbar(p, message);
        }
    }

}
