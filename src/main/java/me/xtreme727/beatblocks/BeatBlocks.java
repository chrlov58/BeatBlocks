package me.xtreme727.beatblocks;

import me.xtreme727.beatblocks.commands.CommandManager;
import me.xtreme727.beatblocks.listeners.EditorListeners;
import me.xtreme727.beatblocks.users.User;
import me.xtreme727.beatblocks.users.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BeatBlocks extends JavaPlugin implements Listener {

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(new EditorListeners(), this);
        CommandManager.getInstance().onEnable(this);
    }

    public void onDisable() {
        for (User u : UserManager.getInstance().getUsers()) {
            if (u.editor) {
                u.toggleEditor();
                u.sendMessage(Message.format(Message.disableEditorMessage));
            }
        }
    }

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("BeatBlocks");
    }

}
