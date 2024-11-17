package me.xtreme727.beatblocks.soundtools;

import me.xtreme727.beatblocks.Message;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;

public enum Dynamic {

    PIANO("Piano",0.25F),
    MEZZO_PIANO("Mezzo-piano", 0.5F),
    MEZZO_FORTE("Mezzo-forte", 0.75F),
    FORTE("Forte", 1F);

    private String dText;
    private float v;

    private Dynamic(String dText, float v) {
        this.dText = dText;
        this.v = v;
    }

    public String getDisplayText() {
        return dText;
    }

    public float getNumericVolume() {
        return v;
    }

    public static Dynamic fromBlock(Block instrumentBlock) {
        if (!(instrumentBlock.getRelative(0, -1, 0).getState() instanceof Sign)) return null;
        Sign s = (Sign) instrumentBlock.getRelative(0, -1, 0).getState();

        for (Dynamic d : values()) {
            if (d.getDisplayText().equalsIgnoreCase(Message.stripColor(s.getSide(Side.FRONT).line(2)))) return d;
        }
        return null;
    }

}
