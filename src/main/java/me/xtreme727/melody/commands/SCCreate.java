package me.xtreme727.melody.commands;

import me.xtreme727.melody.Message;
import me.xtreme727.melody.soundtools.SongManager;
import me.xtreme727.melody.users.User;
import org.bukkit.Material;

public class SCCreate implements MSubCommand {

    public boolean onCommand(User u, String[] args) {
        if (args.length < 3) return false;

        if (u.getTargetBlock().getType() != Material.REDSTONE_LAMP) {
            u.sendMessage(Message.format(Message.commandCreateBlocktype));
            return true;
        }

        int bpm;
        try {
            bpm = Integer.parseInt(args[1]);
        } catch (Exception e) {
            return false;
        }

        StringBuilder s = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            s.append(args[i]).append(" ");
        }
        String name = s.toString();
        if (name.endsWith(" ")) {
            name = name.substring(0, name.length() - 1);
        }

        SongManager.getInstance().create(name, bpm, u.getTargetBlock());
        u.sendMessage(Message.format(Message.commandCreate, name, String.valueOf(bpm)));
        return true;
    }

    public String getCommandFormat() {
        return "create [tempo (BPM)] [name]";
    }

}
