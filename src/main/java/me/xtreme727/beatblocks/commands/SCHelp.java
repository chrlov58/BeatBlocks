package me.xtreme727.beatblocks.commands;

import me.xtreme727.beatblocks.BeatBlocks;
import me.xtreme727.beatblocks.Message;
import me.xtreme727.beatblocks.users.User;

public class SCHelp implements BBSubCommand {

    public boolean onCommand(User u, String[] args) {
        u.sendMessage(Message.format(Message.commandHelpHeader, BeatBlocks.getPlugin().getPluginMeta().getVersion()));
        for (BBSubCommand c : CommandManager.getInstance().getCommands().values()) {
            u.sendMessage(Message.format(Message.commandHelpList, c.getCommandFormat()));
        }
        return true;
    }

    public String getCommandFormat() {
        return "help";
    }
}
