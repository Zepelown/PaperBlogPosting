package org.blog.paperedu.user.management.service;

import java.util.HashMap;
import java.util.UUID;

import org.blog.paperedu.user.management.entity.User;
import org.bukkit.entity.Player;


import static org.blog.paperedu.common.util.HashMapUtil.findKeyByValue;

public class UserManager {
    private HashMap<UUID, User> onlineUserData = new HashMap<>();

    public void addUser(Player player) {
        UUID playerUUID = player.getUniqueId();
        User newUser = new User(
                playerUUID,
                player.getDisplayName(),
                "newbie",
                1000L,
                "jobless", "[뉴비]");
        onlineUserData.put(playerUUID, newUser);
    }

    public void removeUser(UUID playerUUID){
        onlineUserData.remove(playerUUID);
    }

    public User getUserData(UUID playerUUID){
        return onlineUserData.get(playerUUID);
    }

    public User getUserData(String playerName){
        return onlineUserData.get(findKeyByValue(onlineUserData, new User(playerName)));
    }

}
