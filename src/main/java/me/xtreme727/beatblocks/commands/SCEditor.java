package me.xtreme727.beatblocks.commands;

import me.xtreme727.beatblocks.Message;
import me.xtreme727.beatblocks.soundtools.Dynamic;
import me.xtreme727.beatblocks.soundtools.Instrument;
import me.xtreme727.beatblocks.soundtools.Measure;
import me.xtreme727.beatblocks.soundtools.Note;
import me.xtreme727.beatblocks.users.User;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class SCEditor implements BBSubCommand {

    public boolean onCommand(User u, String[] args) {
        u.toggleEditor();
        u.sendMessage(Message.format(Message.commandEditor + (u.editor ? Message.commandEditorAddOn : "")));

        if (u.editor) {
            for (int i : getEditorItems().keySet()) {
                if (i >= 0) {
                    u.getInventory().setItem(i, getEditorItems().get(i));
                }
            }
        }
        return true;
    }

    public String getCommandFormat() {
        return "editor";
    }

    public static HashMap<Integer, ItemStack> getEditorItems() {
        HashMap<Integer, ItemStack> items = new HashMap<Integer, ItemStack>();
        for (Instrument i : Instrument.values()) {
            items.put(i.getItemSlot(), i.getDisplayItem());
        }

        for (Note n : Note.values()) {
            if (n.getItemSlot() >= 0) {
                items.put(n.getItemSlot(), n.getDisplayItem());
            }
        }

        items.put(28, Dynamic.getDynamicItemStack());
        items.put(29, Note.getFlatItemStack());
        items.put(30, Note.getSharpItemStack());
        items.put(32, Measure.values()[0].getMeasureItemStack());
        items.put(33, Measure.getLineCapItemStack());

        ItemStack toolSlot = new ItemStack(Material.BLACK_STAINED_GLASS, 1);
        ItemMeta tSMeta = toolSlot.getItemMeta();
        tSMeta.displayName(Message.formatItemMeta("&7&oClick a tool in your inventory to move it to this slot"));
        toolSlot.setItemMeta(tSMeta);
        items.put(8, toolSlot);

        items.put(-1, new ItemStack(Material.GLASS, 1));
        items.put(-2, new ItemStack(Material.GLOWSTONE, 1));
        items.put(-3, new ItemStack(Material.YELLOW_CONCRETE, 1));

        return items;
    }

}
