package moe.gabriella.popthepop.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Message {

    private static String formatOpen() { return "" + ChatColor.GRAY + "[" + ChatColor.LIGHT_PURPLE + "Pop" + ChatColor.DARK_PURPLE + "The" + ChatColor.LIGHT_PURPLE + "Pop"; }
    private static String formatClosed() { return formatOpen() + ChatColor.GRAY + "] " + ChatColor.WHITE; }
    public static String formatBare(boolean bold) { return ChatColor.LIGHT_PURPLE + (bold ? "" + ChatColor.BOLD : "") + "Pop" + ChatColor.DARK_PURPLE + (bold ? "" + ChatColor.BOLD : "") + "The" + ChatColor.LIGHT_PURPLE + (bold ? "" + ChatColor.BOLD : "") + "Pop"; }
    private static String appendOpen(String toAppend, ChatColor colour) { return formatOpen() + ChatColor.GRAY + ": " + colour + toAppend.toUpperCase() + ChatColor.GRAY + "] " + colour; }

    private static void console(String message) { Bukkit.getServer().getConsoleSender().sendMessage(message); }

    public static void log(String message) { console(formatClosed() + message); }
    public static void warn(String message) { console(appendOpen("Warning", ChatColor.YELLOW) + message); }
    public static void error(String message) { console(appendOpen("Error", ChatColor.RED) + message); }
    public static void debug(String message) { console(appendOpen("Debug", ChatColor.DARK_GREEN) + message); }
    public static void timings(String message) {console(appendOpen("Timings", ChatColor.AQUA) + message); }

    public static String format(String message) { return formatBare(true) + "" + ChatColor.GRAY + " » " + ChatColor.RESET + message;}

    public static String formatTime(int seconds) {
        int s = seconds % 60;
        int h = seconds / 60;
        int m = h % 60;
        h = h / 60;
        if (h == 0)
            return m + "m" + s + "s";
        else
            return h + "h" + m + "m" + s + "s";
    }

    public static String formatTimeFull(int seconds) {
        int s = seconds % 60;
        int h = seconds / 60;
        int m = h % 60;
        h = h / 60;
        if (h == 0)
            return (m < 10 ? "0" + m : "" + m) + ":" + (s < 10 ? "0" + s : "" + s);
        else
            return (h < 10 ? "0" + h : "" + h) + ":" + (m < 10 ? "0" + m : "" + m) + ":" + (s < 10 ? "0" + s : "" + s);
    }
}
