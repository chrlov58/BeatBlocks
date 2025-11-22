package me.xtreme727.beatblocks.commands;

import me.xtreme727.beatblocks.Message;
import me.xtreme727.beatblocks.soundtools.SongManager;
import me.xtreme727.beatblocks.users.User;

public class SCDelete implements BBSubCommand {

    public boolean onCommand(User u, String[] args) {
        if (args.length < 2) return false;

        StringBuilder s = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            s.append(args[i]).append(" ");
        }
        String name = s.toString();
        if (name.endsWith(" ")) {
            name = name.substring(0, name.length() - 1);
        }

        if (SongManager.getInstance().getSong(name) == null) {
            u.sendMessage(Message.format(Message.commandDeleteNull, name));
            return true;
        }

        u.sendMessage(Message.format(Message.commandDelete, SongManager.getInstance().getSong(name).getName()));
        SongManager.getInstance().delete(name);
        return true;
    }

    public String getCommandFormat() {
        return "delete [name]";
    }

}
