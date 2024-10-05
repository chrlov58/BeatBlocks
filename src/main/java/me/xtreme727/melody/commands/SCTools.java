package me.xtreme727.melody.commands;

import me.xtreme727.melody.Melody;
import me.xtreme727.melody.Message;
import me.xtreme727.melody.soundtools.Instrument;
import me.xtreme727.melody.soundtools.Note;
import me.xtreme727.melody.users.User;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;

public class SCTools implements MSubCommand {

    public boolean onCommand(User u, String[] args) {
        if (!u.commandToolsWarning) {
            u.sendMessage(Message.format(Message.commandToolsWarn));
            u.commandToolsWarning = true;
            Bukkit.getServer().getScheduler().runTaskLater(Melody.getPlugin(), new Runnable() {
                public void run() {
                    u.commandToolsWarning = false;
                }
            }, 1200L);
            return true;
        }

        u.getInventory().clear();
        u.sendMessage(Message.format(Message.commandTools));
        for (Instrument i : Instrument.values()) { u.getInventory().setItem(i.getItemSlot(), i.getDisplayItem()); }
        for (Note n : Note.values()) {
            if (n.getItemSlot() >= 0) {
                u.getInventory().setItem(n.getItemSlot(), n.getDisplayItem());
            }
        }
        return true;
    }

    public String getCommandFormat() {
        return "tools";
    }
}
