package me.xtreme727.melody.commands;

import me.xtreme727.melody.Melody;
import me.xtreme727.melody.Message;
import me.xtreme727.melody.users.User;

public class SCHelp implements MSubCommand {

    public boolean onCommand(User u, String[] args) {
        u.sendMessage(Message.format(Message.commandHelpHeader, Melody.getPlugin().getPluginMeta().getVersion()));
        for (MSubCommand c : CommandManager.getInstance().getCommands().values()) {
            u.sendMessage(Message.format(Message.commandHelpList, c.getCommandFormat()));
        }
        return true;
    }

    public String getCommandFormat() {
        return "help";
    }
}
