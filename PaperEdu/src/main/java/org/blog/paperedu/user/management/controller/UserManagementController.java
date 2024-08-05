package org.blog.paperedu.user.management.controller;

import org.blog.paperedu.PaperEdu;
import org.blog.paperedu.user.management.data.entity.UserData;
import org.blog.paperedu.user.management.data.repository.UserDataRepository;
import org.blog.paperedu.user.management.domain.model.User;
import org.blog.paperedu.user.management.domain.service.UserManager;
import org.blog.paperedu.user.management.controller.commands.UserInfoCommand;
import org.blog.paperedu.user.management.view.UserManagementView;
import org.bukkit.configuration.serialization.ConfigurationSerialization;

public class UserManagementController {
    static {
        ConfigurationSerialization.registerClass(UserData.class, "UserData");
    }

    private static UserManager userManager;

    private final UserManagementView userManagementView;

    private final PaperEdu serverInstance;

    private final UserConnectionController userConnectionController;

    private final UserDataRepository userDataRepository;

    public UserManagementController() {
        this.serverInstance = PaperEdu.getServerInstance();
        this.userDataRepository = new UserDataRepository();
        this.userManagementView = new UserManagementView();

        userManager = new UserManager(userDataRepository);

        this.userConnectionController = new UserConnectionController(userManager);

        registerCommands();
        registerEvents();
    }

    public void saveUserData(){
        userDataRepository.saveConfig();
    }


    private void registerEvents() {
        serverInstance.getServer().getPluginManager().registerEvents(userConnectionController, serverInstance);
    }

    private void registerCommands() {
        serverInstance.getServer().getPluginCommand("uinfo").setExecutor(new UserInfoCommand(userManager, userManagementView));
    }


}
