package org.blog.paperedu.user.management.controller.commands;

import org.blog.paperedu.user.management.domain.model.User;
import org.blog.paperedu.user.management.domain.service.UserManager;
import org.blog.paperedu.user.management.view.UserManagementView;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UserInfoCommand implements CommandExecutor {

    private final UserManager userManager;

    private final UserManagementView userManagementView;

    public UserInfoCommand(UserManager userManager, UserManagementView userManagementView){
        this.userManager = userManager;
        this.userManagementView = userManagementView;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
                             @NotNull String[] args) {
        User playerData;
        if (args.length == 1) {
            playerData = userManager.getUserData(args[0]);
            if (playerData == null) {
                sender.sendMessage("유저를 찾을 수 없습니다");
                return false;
            }
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage("플레이어만 이 명령어를 사용할 수 있습니다.");
                return false;
            }
            Player player = (Player) sender;
            playerData = userManager.getUserData(player.getUniqueId());
        }

        userManagementView.sendUserInfo((Player) sender, playerData);

        playerData.setMoney(playerData.getMoney() + 10);

        return true;
    }
}
