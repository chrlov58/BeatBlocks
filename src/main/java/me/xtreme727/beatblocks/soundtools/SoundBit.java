package me.xtreme727.beatblocks.soundtools;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class SoundBit {

    private Instrument instrument;
    private Note note;
    private Dynamic dynamic;
    private int octave;

    public SoundBit(Instrument instrument, Note note, Dynamic dynamic, int octave) {
        this.instrument = instrument;
        this.note = note;
        this.dynamic = dynamic;
        this.octave = octave;
        //Bukkit.getServer().broadcast(Component.text("Note from SOUNDBIT in instantiation: " + note.toString() + " " + note.getPitch() + "P " + note.getOctave() + "O"));
    }

    public Dynamic getDynamics() { return dynamic; }

    public Instrument getInstrument() {
        return instrument;
    }

    public Note getNote() {
        return note;
    }

    public int getOctave() { return octave; }

    public static SoundBit fromBlock(Block instrumentBlock, Dynamic d) {
        for (int i = 0; i <= 5; i++) {
            if (instrumentBlock.getRelative(0, i, 0).getType() == Material.AIR) {
                for (Note n : Note.values()) {

                    Instrument ins = Instrument.fromBlock(instrumentBlock);
                    if ((ins == Instrument.BASS_DRUM || ins == Instrument.STICKS || ins == Instrument.SNARE_DRUM) && i == 1) {
                        return new SoundBit(ins, Note.F_SHARP, d, 2);
                    }

                    if (instrumentBlock.getRelative(0, i-1, 0).getType() == Material.POLISHED_DIORITE_SLAB) {
                        if (instrumentBlock.getRelative(0, i-2, 0).getType() == n.getDisplayMaterial()) {
                            n = Note.getSharp(n);
                            return new SoundBit(ins, n, d, n == Note.F_SHARP ? i-1 : i-2);
                        }
                    }

                    if (instrumentBlock.getRelative(0, i-1, 0).getType() == Material.IRON_TRAPDOOR) {
                        if (instrumentBlock.getRelative(0, i-2, 0).getType() == n.getDisplayMaterial()) {
                            n = Note.getFlat(n);
                            return new SoundBit(ins, n, d, i-2);
                        }
                    }

                    if (instrumentBlock.getRelative(0, i-1, 0).getType() == n.getDisplayMaterial()) {
                        return new SoundBit(ins, n, d, i-1);
                    }
                }
            }
        }
        return null;
    }

}
