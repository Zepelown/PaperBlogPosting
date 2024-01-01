package org.blog.paperedu.user.management.controller;

import org.blog.paperedu.PaperEdu;
import org.blog.paperedu.user.management.service.UserManager;
import org.blog.paperedu.user.management.controller.commands.UserInfoCommand;

public class UserManagementController {

    private static UserManager userManager;

    private final PaperEdu serverInstance;

    private UserConnectionController userConnectionController;

    public UserManagementController() {
        this.userManager = new UserManager();
        this.serverInstance = PaperEdu.getServerInstance();

        this.userConnectionController = new UserConnectionController(userManager);

        registerCommands();
        registerEvents();
    }


    private void registerEvents() {
        serverInstance.getServer().getPluginManager().registerEvents(userConnectionController, serverInstance);
    }

    private void registerCommands() {
        serverInstance.getServer().getPluginCommand("uinfo").setExecutor(new UserInfoCommand(userManager));
    }
}
