package org.blog.paperedu.user.management.domain.service;

import java.util.HashMap;
import java.util.UUID;

import org.blog.paperedu.user.management.domain.model.User;
import org.blog.paperedu.user.management.data.repository.UserDataRepository;
import org.bukkit.entity.Player;


import static org.blog.paperedu.common.util.HashMapUtil.findKeyByValue;

public class UserManager {
    private HashMap<UUID, User> onlineUserData = new HashMap<>();

    private final UserDataRepository userDataRepository;

    public UserManager(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    public void addNewUser(Player player) {
        UUID playerUUID = player.getUniqueId();
        User newUser = new User(
                playerUUID,
                player.getDisplayName(),
                "newbie",
                1000.0,
                "jobless", "[뉴비]");
        onlineUserData.put(playerUUID, newUser);
        userDataRepository.storeUserData(newUser);
    }

    public void addUser(Player player){
        User userData = userDataRepository.loadUserData(player.getUniqueId());
        onlineUserData.put(player.getUniqueId(), userData);
    }

    public void removeUser(UUID playerUUID){
        User userData = onlineUserData.remove(playerUUID);
        userDataRepository.storeUserData(userData);
    }

    public boolean hasUser(UUID uuid){
        return userDataRepository.hasUser(uuid);
    }

    public User getUserData(UUID playerUUID){
        return onlineUserData.get(playerUUID);
    }

    public User getUserData(String playerName){
        return onlineUserData.get(findKeyByValue(onlineUserData, new User(playerName)));
    }

}
