package org.blog.paperedu;

import org.blog.paperedu.user.management.controller.UserManagementController;
import org.bukkit.plugin.java.JavaPlugin;

public final class PaperEdu extends JavaPlugin {
    private static PaperEdu serverInstance;
    private static UserManagementController userManagement;


    @Override
    public void onEnable() {
        serverInstance = this;
        userManagement = new UserManagementController();

        getLogger().info("플러그인 시작 테스트");
    }

    @Override
    public void onDisable() {
        serverInstance = null;
        userManagement = null;

        getLogger().info("플러그인 종료 테스트");
    }

    public static PaperEdu getServerInstance() {
        return serverInstance;
    }

    public static UserManagementController getUserManagement(){
        return userManagement;
    }
}
