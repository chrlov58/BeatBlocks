package me.xtreme727.melody;

import org.bukkit.Bukkit;
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
        try {
            file.createNewFile();
            Bukkit.getServer().broadcast(Message.format(Message.fileSetupSuccess, fName + ".yml"));
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getServer().broadcast(Message.format(Message.fileSetupFailure, fName + ".yml", e.getLocalizedMessage()));
        }

        this.configuration = (FileConfiguration) YamlConfiguration.loadConfiguration(file);
    }

    private void save() {
        try {
            configuration.save(file);
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getServer().broadcast(Message.format(Message.fileSaveFailure, file.getName()));
        }
    }

}
