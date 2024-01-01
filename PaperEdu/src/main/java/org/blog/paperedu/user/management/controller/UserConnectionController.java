package org.blog.paperedu.user.management.controller;

import org.blog.paperedu.PaperEdu;
import org.blog.paperedu.user.management.service.UserManager;
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
        userManager.addUser(event.getPlayer());
        PaperEdu.getServerInstance().getLogger().info("플레이어 데이터 저장");
    }

    @EventHandler
    public void onUserKickFromServer(PlayerKickEvent event) {
        userManager.removeUser(event.getPlayer());
        PaperEdu.getServerInstance().getLogger().info("플레이어 데이터 삭제");
    }

    @EventHandler
    public void onUserQuitFromServer(PlayerQuitEvent event) {
        userManager.removeUser(event.getPlayer());
        PaperEdu.getServerInstance().getLogger().info("플레이어 데이터 삭제");
    }
}
