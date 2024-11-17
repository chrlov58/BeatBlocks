package me.xtreme727.beatblocks.soundtools;

import me.xtreme727.beatblocks.Message;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public enum Measure {

    SIXTEEN(16, 4, "16 Blocks &o(4/4 time)"),
    TWELVE_4(12, 4, "12 Blocks &o(3/4 time)"),
    TWELVE_3(12, 3, "12 Blocks &o(12/8 time)"),
    EIGHT_4(8, 4, "8 Blocks &o(2/4 time)"),
    EIGHT_2(8, 2, "8 Blocks &o(4/4 time)"),
    SIX(6, 2, "6 Blocks &o(3/4 time)"),
    FOUR(4, 1, "4 Blocks &o(4/4 time)"),
    THREE(3, 1, "3 Blocks &o(3/4 time)");

    private int size;
    private int multiplier;
    private String dText;
    private int[] beatBlocks;

    private Measure(int size, int multiplier, String dText, int... beatBlocks) {
        this.size = size;
        this.multiplier = multiplier;
        this.dText = dText;
        this.beatBlocks = beatBlocks;
    }

    public String getDisplayText() {
        return dText;
    }

    public ItemStack getMeasureItemStack() {
        ItemStack i = new ItemStack(Material.MAGMA_CREAM, 1);
        ItemMeta iMeta = i.getItemMeta();
        iMeta.displayName(Message.formatItemMeta("&bMeasure Builder: &f" + dText));

        ArrayList<Component> lore = new ArrayList<Component>();
        lore.add(Message.formatItemMeta("&7LEFT click to cycle through options, "));
        lore.add(Message.formatItemMeta("&7RIGHT click a start-block/end of existing measure to build."));
        lore.add(Component.text(""));
        lore.add(Message.formatItemMeta("&fOptions..."));

        Measure m = (ordinal() == Measure.values().length - 1) ? Measure.values()[0] : Measure.values()[ordinal() + 1];
        while (m != this) {
            lore.add(Message.formatItemMeta(" &f> " + m.getDisplayText()));
            m = (m.ordinal() == Measure.values().length - 1) ? Measure.values()[0] : Measure.values()[m.ordinal() + 1];
        }
        iMeta.lore(lore);
        i.setItemMeta(iMeta);
        return i;
    }

    public static Measure fromItemStack(ItemStack itemStack) {
        if (itemStack.getType() != Material.MAGMA_CREAM) return null;
        ItemMeta meta = itemStack.getItemMeta();
        String name = Message.stripColor(meta.displayName()).replaceAll("Measure Builder: ", "");

        for (Measure m : values()) {
            if (Message.stripColor(Message.formatItemMeta(m.getDisplayText())).equalsIgnoreCase(name)) return m;
        }
        return null;
    }

}
