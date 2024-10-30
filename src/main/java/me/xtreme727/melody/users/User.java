package me.xtreme727.melody.users;

import me.xtreme727.melody.soundtools.Instrument;
import me.xtreme727.melody.soundtools.Note;
import me.xtreme727.melody.soundtools.SoundBit;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class User {

    private Player player;

    public boolean editor;
    private boolean editorFlying;
    private GameMode editorGameMode;
    private ItemStack[] editorInventory;
    private ItemStack[] editorInventoryArmor;
    private ItemStack editorInventoryOffHand;

    public User(Player player) {
        this.player = player;
        editor = false;
    }

    public void sendMessage(Component component) {
        player.sendMessage(component);
    }

    public void playSoundBit(SoundBit bit) {
        player.playSound(player, bit.getInstrument().getBukkitInstrument().getSound(), bit.getDynamics().getNumericVolume(), bit.getNote().getPitch());
    }

    public PlayerInventory getInventory () {
        return player.getInventory();
    }

    public Block getTargetBlock() {
        return player.getTargetBlockExact(4);
    }

    public void toggleEditor() {
        if (editor) {
            editor = false;
            player.getInventory().clear();
            player.getInventory().setContents(editorInventory);
            player.getInventory().setArmorContents(editorInventoryArmor);
            player.getInventory().setItemInOffHand(editorInventoryOffHand);
            player.setAllowFlight(editorFlying);
            player.setGameMode(editorGameMode);
            return;
        }

        editor = true;
        editorInventory = player.getInventory().getContents();
        editorInventoryArmor = player.getInventory().getArmorContents();
        editorInventoryOffHand = player.getInventory().getItemInOffHand();
        player.getInventory().clear();
        for (Instrument i : Instrument.values()) {
            player.getInventory().setItem(i.getItemSlot(), i.getDisplayItem());
        }
        for (Note n : Note.values()) {
            if (n.getItemSlot() >= 0) {
                player.getInventory().setItem(n.getItemSlot(), n.getDisplayItem());
            }
        }

        editorFlying = player.getAllowFlight();
        editorGameMode = player.getGameMode();
        player.setGameMode(GameMode.SURVIVAL);

        player.setAllowFlight(true);
        player.setFlying(true);
    }

}
