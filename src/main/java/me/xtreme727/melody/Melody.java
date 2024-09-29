package me.xtreme727.melody;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Melody extends JavaPlugin {

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("Melody");
    }

}
