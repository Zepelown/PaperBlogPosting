package org.blog.paperedu;

import org.blog.paperedu.user.management.controller.UserManagementController;
import org.blog.paperedu.user.management.data.entity.UserData;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class PaperEdu extends JavaPlugin {

    private static PaperEdu serverInstance;
    private static UserManagementController userManagement;


    @Override
    public void onEnable() {
        getLogger().info("플러그인 시작 테스트");
        serverInstance = this;
        userManagement = new UserManagementController();



    }

    @Override
    public void onDisable() {
        userManagement.saveUserData();
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
