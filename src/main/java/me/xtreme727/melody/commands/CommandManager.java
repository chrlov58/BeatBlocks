package me.xtreme727.melody.commands;

import me.xtreme727.melody.Melody;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CommandManager {

    private static final CommandManager instance =  new CommandManager();
    public static CommandManager getInstance () { return instance; }

    private HashMap<String, MSubCommand> commands;
    public CommandManager() {
        commands = new HashMap<String, MSubCommand>();
        commands.put("help", new SCHelp());
        commands.put("tools", new SCTools());
    }

    public HashMap<String, MSubCommand> getCommands() { return commands; }

    public void onEnable(JavaPlugin javaPlugin) {
        javaPlugin.getCommand("melody").setExecutor(new CommandMelody());
    }

}
