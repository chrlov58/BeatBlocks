package me.xtreme727.beatblocks.commands;

import me.xtreme727.beatblocks.Message;
import me.xtreme727.beatblocks.soundtools.SongManager;
import me.xtreme727.beatblocks.users.User;
import org.bukkit.Material;

public class SCCreate implements BBSubCommand {

    public boolean onCommand(User u, String[] args) {
        if (args.length < 4) return false;

        if (u.getTargetBlock().getType() != Material.REDSTONE_LAMP) {
            u.sendMessage(Message.format(Message.commandCreateBlocktype));
            return true;
        }

        int bpm;
        int div;
        try {
            bpm = Integer.parseInt(args[1]);
            div = Integer.parseInt(args[2]);
        } catch (Exception e) {
            return false;
        }

        if (1 > div || 4 < div) return false;

        StringBuilder s = new StringBuilder();
        for (int i = 3; i < args.length; i++) {
            s.append(args[i]).append(" ");
        }
        String name = s.toString();
        if (name.endsWith(" ")) {
            name = name.substring(0, name.length() - 1);
        }

        if (SongManager.getInstance().getSong(name) != null) {
            u.sendMessage(Message.format(Message.commandCreateNotNull, name));
            return true;
        }

        SongManager.getInstance().create(name, bpm, div, u.getTargetBlock());
        u.sendMessage(Message.format(Message.commandCreate, name, String.valueOf(bpm)));
        return true;
    }

    public String getCommandFormat() {
        return "create [tempo (BPM)] [divisions per beat (1-4)] [name]";
    }

}
