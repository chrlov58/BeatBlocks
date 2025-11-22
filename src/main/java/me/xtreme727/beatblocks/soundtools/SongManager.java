package me.xtreme727.beatblocks.soundtools;

import me.xtreme727.beatblocks.SettingsManager;
import org.bukkit.block.Block;

import java.util.ArrayList;

public class SongManager {

    private static SongManager instance = new SongManager();
    public static SongManager getInstance() {
        return instance;
    }

    private ArrayList<Song> songs;
    public SongManager() {
        songs = new ArrayList<Song>();
    }

    public void onEnable() {
        for (String key : SettingsManager.getSongFile().getKeys()) {
            Song s = new Song(key);
            songs.add(s);
        }
    }

    public void delete(String name) {
        SettingsManager.getSongFile().set(name, null);
        Song s = getSong(name);
        songs.remove(s);
    }

    public Song create(String name, int bpm, int divisions, Block b) {
        if (getSong(name) != null) return getSong(name);
        SettingsManager.getSongFile().set(name + ".BPM", bpm);
        SettingsManager.getSongFile().set(name + ".Div", divisions);
        SettingsManager.getSongFile().saveLocation(name, b.getLocation());

        Song s = new Song(name);
        s.load();
        songs.add(s);
        return s;
    }

    public Song getSong(String name) {
        for (Song s : songs) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

}
