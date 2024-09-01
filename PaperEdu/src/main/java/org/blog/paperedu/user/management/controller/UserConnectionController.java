package org.blog.paperedu.user.management.controller;

import org.blog.paperedu.user.management.domain.service.UserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserConnectionController implements Listener {

    private final UserManager userManager;

    UserConnectionController(UserManager userManager){
        this.userManager = userManager;
    }

    @EventHandler
    public void onUserJoinServer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore() || !userManager.hasUser(player.getUniqueId())){
            userManager.addNewUser(event.getPlayer());
            return;
        }
        userManager.addUser(player);
        player.getPlayerTime();
    }

    @EventHandler
    public void onUserKickFromServer(PlayerKickEvent event) {
        userManager.removeUser(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onUserQuitFromServer(PlayerQuitEvent event) {
        userManager.removeUser(event.getPlayer().getPlayerProfile().getId());
    }
}
