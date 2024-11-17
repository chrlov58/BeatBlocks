package me.xtreme727.beatblocks.commands;

import me.xtreme727.beatblocks.Message;
import me.xtreme727.beatblocks.soundtools.Song;
import me.xtreme727.beatblocks.soundtools.SongManager;
import me.xtreme727.beatblocks.users.User;

public class SCPlay implements BBSubCommand {

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

        Song song = SongManager.getInstance().getSong(name);

        if (song == null) {
            u.sendMessage(Message.format(Message.commandPlayNull, name));
            return true;
        }

        song.play(u);
        u.sendMessage(Message.format(Message.commandPlay, song.getName(), String.valueOf(song.getTempo())));

        return true;
    }

    public String getCommandFormat() {
        return "play [name]";
    }

}
