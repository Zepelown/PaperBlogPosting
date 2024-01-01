package org.blog.paperedu.user.management.service;

import java.util.HashMap;
import org.blog.paperedu.user.management.entity.User;
import org.bukkit.entity.Player;

public class UserManager {
    private HashMap<Player, User> onlineUserData = new HashMap<>();

    public void addUser(Player player) {
        User newUser = new User(
                player.getUniqueId(),
                player.getDisplayName(),
                "newbie",
                1000L,
                "jobless", "[뉴비]");
        onlineUserData.put(player, newUser);
    }

    public void removeUser(Player player){
        onlineUserData.remove(player);
    }

    public User getUserData(Player player){
        return onlineUserData.get(player);
    }

}
