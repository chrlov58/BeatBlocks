package me.xtreme727.beatblocks.soundtools;

import me.xtreme727.beatblocks.BeatBlocks;
import me.xtreme727.beatblocks.SettingsManager;
import me.xtreme727.beatblocks.users.User;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class Song {

    private String name;
    private HashMap<Integer, Division> divs;
    private int divisions;
    private int tempo;
    private Block startPoint;

    public Song(String name) {
        this.name = name;
        divs = new HashMap<Integer, Division>();
        startPoint = SettingsManager.getSongFile().getLocation(name).getBlock();
        tempo = SettingsManager.getSongFile().getInt(name + ".BPM");
        divisions = SettingsManager.getSongFile().getInt(name + ".Div");
    }

    public String getName () { return name; }

    public void load() {
        divs.clear();
        Block readPoint = startPoint;
        int i = 1;
        int count = 1;
        Dynamic currentDynamic = Dynamic.FORTE;
        while (readPoint.getType() != Material.AIR) {
            Bukkit.getServer().broadcast(Component.text("The readpoint isn't air"));
            Division division = new Division();
            Dynamic newDynamic = Dynamic.fromBlock(readPoint.getRelative(i, 0, 0));
            int z = 1;

            if (newDynamic != null) currentDynamic = newDynamic;
            while (readPoint.getRelative(i, 0, z).getType() != Material.AIR) {
                Block soundBlock = readPoint.getRelative(i, 0, z);
                SoundBit bit = new SoundBit(Instrument.fromBlock(soundBlock), Note.fromBlock(soundBlock), currentDynamic);
                Bukkit.getServer().broadcast(Component.text("Adding note to division " + count + " [" + bit.getNote().toString() + ", "
                  + bit.getInstrument().toString() + ", " + bit.getDynamics().toString() + "]"));
                division.getSoundBits().add(bit);
                z++;
            }
            Bukkit.getServer().broadcast(Component.text("Added all notes (" + division.getSoundBits().size() + ") to a division."));
            divs.put(count, division);

            if (readPoint.getRelative(i, 0, 0).getType() == Material.REDSTONE_LAMP) {
                Block b = readPoint.getRelative(i, 0, 0);
                if (SettingsManager.getSongFile().getLocation(name + "." + b.getX() + "-" + b.getY() + "-" + b.getZ()) != null) {
                    Location newReadPointLoc = SettingsManager.getSongFile().getLocation(name + "." + b.getX() + "-" + b.getY() + "-" + b.getZ());
                    Block newReadPoint = newReadPointLoc.getBlock();
                    if (newReadPoint.getType() != Material.REDSTONE_LAMP) return;
                    readPoint = newReadPoint;
                    i = 0;
                }
            }

            if (readPoint.getRelative(i, 0, 0).getType() == Material.AIR) return;
            i++;
            count++;
        }
    }

    public void play(User u) {
        new BukkitRunnable() {
            int i = 1;
            public void run() {
                if (divs.get(i) == null) {
                    Bukkit.getServer().broadcast(Component.text("PLAY: End of song"));
                    this.cancel();
                    return;
                }
                for (SoundBit bit : divs.get(i).getSoundBits()) {
                    Bukkit.getServer().broadcast(Component.text("PLAY: Playing note " + bit.getNote().toString()
                            + " [" + "P" + bit.getNote().getPitch() + ", " + bit.getInstrument().toString() + ", " + bit.getDynamics().toString() + "]"));
                    u.playSoundBit(bit);
                }
                i++;
            }
        }.runTaskTimer(BeatBlocks.getPlugin(), 0L, (long) (60*20)/(tempo * divisions));
    }

    public int getTempo() {
        return tempo;
    }

    private long getTempoLong() {
        return (long) (60*20)/tempo;
        //  1 min x 60 sec x 20L     ~ (long) T
        // T beats   1 min  1 sec          1 beat
    }

}
