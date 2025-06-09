package org.blog.paperedu.mob.controller;

import org.blog.paperedu.PaperEdu;
import org.blog.paperedu.mob.controller.commands.BigEyeZombieCommand;

public class MobController {

    private final PaperEdu serverInstance;
    private final MobHitController mobHitController;

    public MobController() {
        this.serverInstance = PaperEdu.getServerInstance();
        this.mobHitController = new MobHitController();
        registerCommands();
        registerEvent();
    }

    private void registerCommands() {
        serverInstance.getServer().getPluginCommand("bigEyeZombie").setExecutor(new BigEyeZombieCommand());
    }

    private void registerEvent() {
        serverInstance.getServer().getPluginManager().registerEvents(mobHitController, serverInstance);
    }
}
