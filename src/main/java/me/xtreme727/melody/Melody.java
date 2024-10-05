package me.xtreme727.melody;

import me.xtreme727.melody.commands.CommandManager;
import me.xtreme727.melody.soundtools.Instrument;
import me.xtreme727.melody.soundtools.Note;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Melody extends JavaPlugin implements Listener {

    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        CommandManager.getInstance().onEnable(this);
    }

    @EventHandler
    public void onBlockClick(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && !e.getPlayer().isSneaking()) {
            Instrument i = Instrument.fromBlock(e.getClickedBlock());
            Note n = Note.fromBlock(e.getClickedBlock());
            assert i != null;
            assert n != null;
            assert i.getBukkitInstrument().getSound() != null;
            e.getPlayer().playSound(e.getPlayer().getLocation(), i.getBukkitInstrument().getSound(), 0.5F, n.getPitch());
        }
    }

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("Melody");
    }

}
