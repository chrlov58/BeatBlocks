package me.xtreme727.beatblocks.commands;

import me.xtreme727.beatblocks.Message;
import me.xtreme727.beatblocks.soundtools.Song;
import me.xtreme727.beatblocks.soundtools.SongManager;
import me.xtreme727.beatblocks.users.User;

public class SCList implements BBSubCommand {

    public boolean onCommand(User u, String[] args) {
        u.sendMessage(Message.format(Message.commandListHeader, "" + SongManager.getInstance().getSongs().size()));

        for (Song s : SongManager.getInstance().getSongs()) {
            u.sendMessage(Message.format(Message.commandListFormat, s.getName(), "" + s.getTempo()));
        }
        return true;
    }

    public String getCommandFormat() {
        return "list";
    }

}
