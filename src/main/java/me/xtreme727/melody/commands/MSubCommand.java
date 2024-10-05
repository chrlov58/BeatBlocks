package me.xtreme727.melody.commands;

import me.xtreme727.melody.users.User;

public interface MSubCommand {

    public abstract boolean onCommand(User u, String[] args);
    public abstract String getCommandFormat();

}
