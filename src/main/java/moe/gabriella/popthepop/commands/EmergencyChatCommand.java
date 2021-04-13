package moe.gabriella.popthepop.commands;

import moe.gabriella.popthepop.PopThePop;
import moe.gabriella.popthepop.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EmergencyChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        PopThePop.emergencyChatMode = !PopThePop.emergencyChatMode;
        sender.sendMessage(Message.format("Emergency Chat is now " + PopThePop.emergencyChatMode));
        return true;
    }
}
