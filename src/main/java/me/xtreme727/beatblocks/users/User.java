package me.xtreme727.beatblocks.users;

import me.xtreme727.beatblocks.soundtools.SoundBit;
import net.kyori.adventure.text.Component;
import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class User {

    private Player player;

    public boolean editor;
    public Block editorCapA;
    public Block editorCapB;
    public boolean editorCapping;
    private boolean editorFlying;
    private GameMode editorGameMode;
    private ItemStack[] editorInventory;
    private ItemStack[] editorInventoryArmor;
    private ItemStack editorInventoryOffHand;

    public User(Player player) {
        this.player = player;
        editor = false;
        editorCapping = false;
    }

    public void closeInventory() {
        player.closeInventory();
    }

    public PlayerInventory getInventory() {
        return player.getInventory();
    }

    public Block getTargetBlock() {
        return player.getTargetBlockExact(4);
    }

    public boolean isSneaking() { return player.isSneaking(); }

    public void playSoundBit(SoundBit bit) {
        player.playSound(player, bit.getInstrument().getBukkitInstrument().getSound(), bit.getDynamics().getNumericVolume(), bit.getNote().getPitch(bit.getOctave()));
    }

    public void sendMessage(Component component) {
        player.sendMessage(component);
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
        player.getInventory().setHeldItemSlot(8);

        editorFlying = player.getAllowFlight();
        editorGameMode = player.getGameMode();
        editorCapA = null;
        editorCapB = null;
        player.setGameMode(GameMode.SURVIVAL);
        player.setFoodLevel(20);

        player.setAllowFlight(true);
        player.setFlying(true);
    }

}
