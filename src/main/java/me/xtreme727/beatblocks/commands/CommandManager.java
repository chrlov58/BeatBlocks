package me.xtreme727.beatblocks.commands;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class CommandManager {

    private static final CommandManager instance =  new CommandManager();
    public static CommandManager getInstance () { return instance; }

    private HashMap<String, BBSubCommand> commands;
    public CommandManager() {
        commands = new HashMap<String, BBSubCommand>();
        commands.put("help", new SCHelp());
        commands.put("editor", new SCEditor());
        commands.put("create", new SCCreate());
        commands.put("play", new SCPlay());
    }

    public HashMap<String, BBSubCommand> getCommands() { return commands; }

    public void onEnable(JavaPlugin javaPlugin) {
        javaPlugin.getCommand("beatblocks").setExecutor(new CommandBeatBlocks());
    }

}
