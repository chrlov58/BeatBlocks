package me.xtreme727.beatblocks.commands;

import me.xtreme727.beatblocks.Message;
import me.xtreme727.beatblocks.soundtools.Instrument;
import me.xtreme727.beatblocks.soundtools.Measure;
import me.xtreme727.beatblocks.soundtools.Note;
import me.xtreme727.beatblocks.users.User;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SCEditor implements BBSubCommand {

    public boolean onCommand(User u, String[] args) {
        u.toggleEditor();
        u.sendMessage(Message.format(Message.commandEditor + (u.editor ? Message.commandEditorAddOn : "")));

        if (u.editor) {
            for (Instrument i : Instrument.values()) {
                u.getInventory().setItem(i.getItemSlot(), i.getDisplayItem());
            }

            for (Note n : Note.values()) {
                if (n.getItemSlot() >= 0) {
                    u.getInventory().setItem(n.getItemSlot(), n.getDisplayItem());
                }
            }

            u.getInventory().setItem(29, Note.getFlatItemStack());
            u.getInventory().setItem(30, Note.getSharpItemStack());
            u.getInventory().setItem(32, Measure.values()[0].getMeasureItemStack());

            ItemStack toolSlot = new ItemStack(Material.BLACK_STAINED_GLASS, 1);
            ItemMeta tSMeta = toolSlot.getItemMeta();
            tSMeta.displayName(Message.formatItemMeta("&7&oClick a tool in your inventory to move it to this slot"));
            toolSlot.setItemMeta(tSMeta);
            u.getInventory().setItem(8, toolSlot);
        }
        return true;
    }

    public String getCommandFormat() {
        return "editor";
    }

}
