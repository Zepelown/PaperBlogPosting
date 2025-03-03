package org.blog.paperedu;

import org.blog.paperedu.server.management.controller.ServerManagementController;
import org.blog.paperedu.user.management.controller.UserManagementController;
import org.blog.paperedu.user.management.data.entity.UserData;
import org.blog.paperedu.weapon.gun.controller.GunController;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class PaperEdu extends JavaPlugin {
    private static PaperEdu serverInstance;
    private static UserManagementController userManagement;
    private static ServerManagementController serverManagement;
    private static GunController gunController;
    @Override
    public void onEnable() {
        getLogger().info("플러그인 시작 테스트");
        serverInstance = this;


        userManagement = new UserManagementController();


        serverManagement = new ServerManagementController();
        gunController = new GunController();
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

    public static GunController getGunController(){return gunController;}

}
