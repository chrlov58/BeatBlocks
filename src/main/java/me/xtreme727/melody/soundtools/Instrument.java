package me.xtreme727.melody.soundtools;

import me.xtreme727.melody.Message;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Instrument {

    BANJO(9, Material.OAK_PLANKS, org.bukkit.Instrument.BANJO, "Banjo"),
    BASS_DRUM(10, Material.COAL_BLOCK, org.bukkit.Instrument.BASS_DRUM, "Kick Drum"),
    BASS_GUITAR(11, Material.OAK_WOOD, org.bukkit.Instrument.BASS_GUITAR, "Bass Guitar"),
    BELL(12, Material.GOLD_BLOCK, org.bukkit.Instrument.BELL, "Bell/Glockenspiel"),
    BIT(13, Material.TARGET, org.bukkit.Instrument.BIT, "Bit/Square"),
    CHIME(14, Material.REINFORCED_DEEPSLATE, org.bukkit.Instrument.CHIME, "Chime"),
    COW_BELL(15, Material.BIRCH_WOOD, org.bukkit.Instrument.COW_BELL, "Cowbell"),
    DIDGERIDOO(16, Material.COMPOSTER, org.bukkit.Instrument.DIDGERIDOO, "Didgeridoo"),
    FLUTE(17, Material.IRON_BLOCK, org.bukkit.Instrument.FLUTE, "Flute"),
    GUITAR(19, Material.PACKED_MUD, org.bukkit.Instrument.GUITAR, "Guitar"),
    IRON_XYLOPHONE(20, Material.SMOOTH_STONE, org.bukkit.Instrument.IRON_XYLOPHONE, "Vibraphone"),
    PIANO(21, Material.QUARTZ_BLOCK, org.bukkit.Instrument.PIANO, "Piano"),
    PLING(22, Material.SEA_LANTERN, org.bukkit.Instrument.PLING, "Pling"),
    SNARE_DRUM(23, Material.WHITE_WOOL, org.bukkit.Instrument.SNARE_DRUM, "Snare Drum"),
    STICKS(24, Material.DIORITE, org.bukkit.Instrument.STICKS, "Sticks/Hi-Hat"),
    XYLOPHONE(25, Material.SPRUCE_PLANKS, org.bukkit.Instrument.XYLOPHONE, "Xylophone");

    private Material dMaterial;
    private org.bukkit.Instrument bInstrument;
    private String dName;
    private ItemStack dItem;
    private int iSlot;

    private Instrument(int iSlot, Material dMaterial, org.bukkit.Instrument bukkitInstrument, String dName) {
        this.dMaterial = dMaterial;
        this.bInstrument = bukkitInstrument;
        this.dName = dName;
        this.iSlot = iSlot;

        dItem = new ItemStack(dMaterial, 1);
        ItemMeta dItemMeta = dItem.getItemMeta();
        dItemMeta.displayName(Message.format("&f&l" + dName).decoration(TextDecoration.ITALIC, false));
        dItem.setItemMeta(dItemMeta);
    }

    public Material getDisplayMaterial() {
        return dMaterial;
    }

    public ItemStack getDisplayItem() {
        return dItem;
    }

    public org.bukkit.Instrument getBukkitInstrument() {
        return bInstrument;
    }

    public int getItemSlot() { return iSlot; }

    public static Instrument fromBlock(Block b) {
        for (Instrument i : Instrument.values()) {
            if (b.getType() == i.getDisplayMaterial()) {
                return i;
            }
        }
        return null;
    }

}
