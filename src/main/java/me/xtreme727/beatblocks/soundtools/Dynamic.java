package me.xtreme727.beatblocks.soundtools;

import me.xtreme727.beatblocks.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

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

    public static ItemStack getDynamicItemStack() {
        ItemStack i = new ItemStack(Material.OAK_HANGING_SIGN, 1);
        ItemMeta iMeta = i.getItemMeta();
        iMeta.displayName(Message.formatItemMeta("&f&lDynamics"));
        ArrayList<Component> lore = new ArrayList<Component>();
        lore.add(Message.formatItemMeta("&7Write the first line as &bP&7, &bMP&7, &bMF&7, or &bF&7 to set dynamics."));
        iMeta.lore(lore);
        i.setItemMeta(iMeta);
        return i;
    }

}
