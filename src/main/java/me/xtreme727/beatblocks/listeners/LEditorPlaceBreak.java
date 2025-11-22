package me.xtreme727.beatblocks.listeners;

import me.xtreme727.beatblocks.commands.SCEditor;
import me.xtreme727.beatblocks.users.User;
import me.xtreme727.beatblocks.users.UserManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class LEditorPlaceBreak implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        User u = UserManager.getInstance().getUser(event.getPlayer());
        if (!u.editor) return;

        if (event.getBlockPlaced().getType() == Material.BLACK_STAINED_GLASS) {
            event.setCancelled(true);
            return;
        }
        for (ItemStack i : SCEditor.getEditorItems().values()) {
            if (event.getBlockPlaced().getType() == i.getType()) {
                u.getInventory().setItemInMainHand(event.getItemInHand());
            }
        }
    }

}
