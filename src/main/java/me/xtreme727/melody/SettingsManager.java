package me.xtreme727.melody;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SettingsManager {

    private static SettingsManager songs = new SettingsManager("Songs");
    public static SettingsManager getSongFile() {
        return songs;
    }

    private File file;
    private FileConfiguration configuration;

    private SettingsManager(String fName) {
        if (!Melody.getPlugin().getDataFolder().exists()) {
            Melody.getPlugin().getDataFolder().mkdir();
        }

        this.file = new File(Melody.getPlugin().getDataFolder(), fName + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                Bukkit.getServer().broadcast(Message.format(Message.fileSetupSuccess, fName + ".yml"));
            } catch (Exception e) {
                e.printStackTrace();
                Bukkit.getServer().broadcast(Message.format(Message.fileSetupFailure, fName + ".yml", e.getLocalizedMessage()));
            }
        }

        this.configuration = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
    }

    public int getInt(String path) {
        return configuration.getInt(path);
    }

    public Location getLocation(String path) {
        try {
            World w = Bukkit.getServer().getWorld(configuration.getString(path + ".World"));
            double x = configuration.getDouble(path + ".X");
            double y = configuration.getDouble(path + ".Y");
            double z = configuration.getDouble(path + ".Z");
            Location l = new Location(w, x, y, z);
            return l;
        } catch (Exception e) {
            return null;
        }
    }

    private void save() {
        try {
            configuration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getServer().broadcast(Message.format(Message.fileSaveFailure, file.getName(), e.getLocalizedMessage()));
        }
    }

    public void saveLocation(String path, Location l) {
        set(path + ".World", l.getWorld().getName());
        set(path + ".X", l.getX());
        set(path + ".Y", l.getY());
        set(path + ".Z", l.getZ());
    }

    public void set(String path, Object value) {
        configuration.set(path, value);
        save();
    }

}
