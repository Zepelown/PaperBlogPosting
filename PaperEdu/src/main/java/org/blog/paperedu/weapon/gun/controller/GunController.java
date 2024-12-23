package org.blog.paperedu.weapon.gun.controller;

import org.blog.paperedu.PaperEdu;
import org.blog.paperedu.weapon.gun.controller.command.GetBulletCommand;
import org.blog.paperedu.weapon.gun.controller.command.GetGunCommand;
import org.blog.paperedu.weapon.gun.domain.model.gun.Gun;
import org.blog.paperedu.weapon.gun.domain.model.task.ReloadScheduler;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GunController {

    private final GunPlayerInteractController gunPlayerInteractController;
    private final ReloadScheduler reloadScheduler;
    private final PaperEdu serverInstance;


    public GunController() {
        gunPlayerInteractController = new GunPlayerInteractController();
        reloadScheduler = new ReloadScheduler();
        serverInstance = PaperEdu.getServerInstance();

        registerEvents();
        registerCommands();
    }

    public void reload(Player player, Gun gun, ItemStack gunItemStack, int bulletCount, int reloadTime) {
        reloadScheduler.reload(player, gun, gunItemStack, bulletCount, reloadTime);
    }

    private void registerEvents() {
        serverInstance.getServer().getPluginManager().registerEvents(gunPlayerInteractController, serverInstance);
    }

    private void registerCommands() {
        serverInstance.getServer().getPluginCommand("getGun").setExecutor(new GetGunCommand());
        serverInstance.getServer().getPluginCommand("getBullet").setExecutor(new GetBulletCommand());
    }
}
