package me.xtreme727.beatblocks.listeners;

import me.xtreme727.beatblocks.soundtools.Measure;
import me.xtreme727.beatblocks.users.User;
import me.xtreme727.beatblocks.users.UserManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EditorListeners implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        User u = UserManager.getInstance().getUser(event.getPlayer());

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            Measure m = Measure.fromItemStack(u.getInventory().getItemInMainHand());
            if (m != null && u.editor) {
                u.getInventory().setItem(u.getInventory().getHeldItemSlot(), Measure.values()[(m.ordinal() == Measure.values().length - 1) ? 0 : m.ordinal() + 1].getMeasureItemStack());
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        User u = UserManager.getInstance().getUser((Player) event.getWhoClicked());
        if (!u.editor || event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if (u.getInventory() != event.getClickedInventory()) return;

        Measure m = Measure.fromItemStack(event.getCurrentItem());
        if (m != null && event.getSlot() == 32) {
            u.getInventory().setItem(8, Measure.values()[0].getMeasureItemStack());
        }
        if (m != null && event.getSlot() == 8) {
            u.getInventory().setItem(8, Measure.values()[(m.ordinal() == Measure.values().length - 1) ? 0 : m.ordinal() + 1].getMeasureItemStack());
        }

        if (event.getSlot() > 8) {
            u.getInventory().setItem(8, event.getCurrentItem());
        }

        event.setCancelled(true);
        if (event.getSlot() > 8) { u.closeInventory(); }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        User u = UserManager.getInstance().getUser((Player) event.getEntity());

        if (u.editor) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        User u = UserManager.getInstance().getUser(event.getPlayer());
        if (u.editor) { u.toggleEditor(); }
    }

}
