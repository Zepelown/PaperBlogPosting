package org.blog.paperedu.user.management.controller;

import org.blog.paperedu.PaperEdu;
import org.blog.paperedu.user.management.service.UserManager;
import org.blog.paperedu.user.management.controller.commands.UserInfoCommand;
import org.blog.paperedu.user.management.view.UserManagementView;

public class UserManagementController {

    private static UserManager userManager;

    private final UserManagementView userManagementView;

    private final PaperEdu serverInstance;

    private UserConnectionController userConnectionController;

    public UserManagementController() {
        this.userManager = new UserManager();
        this.userManagementView = new UserManagementView();
        this.serverInstance = PaperEdu.getServerInstance();

        this.userConnectionController = new UserConnectionController(userManager);

        registerCommands();
        registerEvents();
    }


    private void registerEvents() {
        serverInstance.getServer().getPluginManager().registerEvents(userConnectionController, serverInstance);
    }

    private void registerCommands() {
        serverInstance.getServer().getPluginCommand("uinfo").setExecutor(new UserInfoCommand(userManager, userManagementView));
    }
}
