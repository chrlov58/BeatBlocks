package me.xtreme727.beatblocks.commands;

import me.xtreme727.beatblocks.users.User;

public interface BBSubCommand {

    public abstract boolean onCommand(User u, String[] args);
    public abstract String getCommandFormat();

}
