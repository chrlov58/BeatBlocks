package me.xtreme727.melody.commands;

import me.xtreme727.melody.Message;
import me.xtreme727.melody.users.User;
import me.xtreme727.melody.users.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandMelody implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command c, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Message.format(Message.consoleCommandSender));
            return true;
        }

        User u = UserManager.getInstance().getUser((Player) sender);

        if (args.length == 0) {
            CommandManager.getInstance().getCommands().get("help").onCommand(u, args);
            return true;
        }

        MSubCommand sc = CommandManager.getInstance().getCommands().get(args[0].toLowerCase());
        if (sc == null) {
            CommandManager.getInstance().getCommands().get("help").onCommand(u, args);
            return true;
        }

        if (!sc.onCommand(u, args)) {
            u.sendMessage(Message.format(Message.commandInvalidFormat, sc.getCommandFormat()));
        }
        return true;
    }

}
