package me.xtreme727.beatblocks.users;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class UserManager {

    private static final UserManager instance = new UserManager();
    public static UserManager getInstance() {
        return instance;
    }

    private HashMap<Player, User> users;
    public UserManager() {
        users = new HashMap<Player, User>();
    }

    public User getUser(Player player) {
        if (users.containsKey(player)) return users.get(player);
        User u = new User(player);
        users.put(player, u);
        return u;
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<User>(users.values());
    }

}
