package me.xtreme727.melody.commands;

import me.xtreme727.melody.Message;
import me.xtreme727.melody.soundtools.Song;
import me.xtreme727.melody.soundtools.SongManager;
import me.xtreme727.melody.users.User;

public class SCPlay implements MSubCommand {

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
