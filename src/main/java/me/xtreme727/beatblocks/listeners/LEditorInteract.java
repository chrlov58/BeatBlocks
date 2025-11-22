package me.xtreme727.beatblocks.listeners;

import me.xtreme727.beatblocks.Message;
import me.xtreme727.beatblocks.SettingsManager;
import me.xtreme727.beatblocks.commands.SCEditor;
import me.xtreme727.beatblocks.soundtools.Measure;
import me.xtreme727.beatblocks.users.User;
import me.xtreme727.beatblocks.users.UserManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.HashMap;

public class LEditorInteract implements Listener {

    private static final HashMap<User, Long> lastClicks = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        User u = UserManager.getInstance().getUser(event.getPlayer());
        if (!u.editor) return;
        if (event.getHand() != EquipmentSlot.HAND) return;

        Measure m = Measure.fromItemStack(u.getInventory().getItemInMainHand());
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (m != null) {
                u.getInventory().setItem(u.getInventory().getHeldItemSlot(), Measure.values()[(m.ordinal() == Measure.values().length - 1) ? 0 : m.ordinal() + 1].getMeasureItemStack());
                event.setCancelled(true);
                return;
            }

            if (event.getClickedBlock() == null || event.getClickedBlock().getType() == Material.AIR) return;
            onInteractBlockBreak(event, u);
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (m == null) return;

            if (!onInteractMeasureBuild(event, u, m)) {
                u.sendMessage(Message.format(Message.editorMeasureBuildError));
            } else if (!onInteractLineConnect(event, u, m)) {
                u.sendMessage(Message.formatItemMeta(Message.editorLineConnectError));
            }
        }
    }

    public void onInteractBlockBreak(PlayerInteractEvent event, User u) {
        if (lastClicks.get(u) != null) {
            if (System.currentTimeMillis() - lastClicks.get(u) < 200) {
                return;
            } else {
                lastClicks.remove(u);
            }
        }

        Material blockType = event.getClickedBlock().getType();
        for (int i : SCEditor.getEditorItems().keySet()) {
            if (blockType == SCEditor.getEditorItems().get(i).getType()) {
                event.setCancelled(true);
                if (i < 0 && !u.isSneaking()) { return; }
                event.getClickedBlock().setType(Material.AIR);
                lastClicks.put(u, System.currentTimeMillis());
            }
        }
    }

    public boolean onInteractLineConnect(PlayerInteractEvent event, User u, Measure m) {
        if (m != Measure.CONNECT) return true;
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock.getType() != Material.REDSTONE_LAMP) return false;
        event.setCancelled(true);

        if (u.editorCapA == null) {
            u.editorCapA = clickedBlock;
            u.sendMessage(Message.format(Message.editorLineSelect + Message.editorLineSelectAddOn, "one"));
            return true;
        }

        u.editorCapB = clickedBlock;
        u.sendMessage(Message.format(Message.editorLineSelect, "two"));
        if (SettingsManager.isSimilarLocation(u.editorCapA.getLocation(), u.editorCapB.getLocation())) {
            for (String key : SettingsManager.getLineCapsFile().getKeys()) {
                if (SettingsManager.isSimilarLocation(SettingsManager.getLineCapsFile().getLocation(key + ".End-Cap"), u.editorCapA.getLocation())) {
                    SettingsManager.getLineCapsFile().set(key, null);
                    u.sendMessage(Message.format(Message.editorLineSimilarDelete, "" + u.editorCapA.getX(), "" + u.editorCapA.getY(), "" + u.editorCapA.getZ()));
                    u.editorCapA = null;
                    u.editorCapB = null;
                    return true;
                }
            }
            u.sendMessage(Message.format(Message.editorLineSimilarCancel, "" + u.editorCapA.getX(), "" + u.editorCapA.getY(), "" + u.editorCapA.getZ()));
            u.editorCapA = null;
            u.editorCapB = null;
            return true;
        }

        for (String key : SettingsManager.getLineCapsFile().getKeys()) {
            if (SettingsManager.isSimilarLocation(SettingsManager.getLineCapsFile().getLocation(key + ".End-Cap"), u.editorCapA.getLocation())) {
                SettingsManager.getLineCapsFile().set(key, null);
            }
        }
        u.sendMessage(Message.format(Message.editorLineConnect,"" + u.editorCapA.getX(), "" + u.editorCapA.getY(), "" + u.editorCapA.getZ(), ""
                + u.editorCapB.getX(), "" + u.editorCapB.getY(), "" + u.editorCapB.getZ()));
        SettingsManager.getLineCapsFile().saveCaps(u.editorCapA, u.editorCapB);
        u.editorCapA = null;
        u.editorCapB = null;

        return true;
    }

    public boolean onInteractMeasureBuild(PlayerInteractEvent event, User u, Measure m) {
        if (m == Measure.CONNECT) return true;
        Block endBlock = event.getClickedBlock();
        if (endBlock.getType() != Material.GLASS && endBlock.getType() != Material.YELLOW_CONCRETE && endBlock.getType() != Material.REDSTONE_LAMP) return false;

        Block startBlock = endBlock.getRelative(1, 0, 0);
        Block[] newBlocks = new Block[m.getSize()];
        for (int i = 0; i < newBlocks.length; i++) {
            Block newBlock = startBlock.getRelative(i, 0, 0);
            if (newBlock.getType() != Material.AIR) return false;
            newBlocks[i] = newBlock;
        }

        for (int i = 0; i < newBlocks.length; i++) {
            if (i % m.getMultiplier() == 0) {
                newBlocks[i].setType(Material.YELLOW_CONCRETE);
            } else {
                newBlocks[i].setType(Material.GLASS);
            }
        }

        startBlock.setType(Material.GLOWSTONE);

        return true;
    }

}
