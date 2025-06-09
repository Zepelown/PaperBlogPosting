package org.blog.paperedu.mob.controller.commands;

import org.blog.paperedu.mob.domain.model.BigEyeZombie;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BigEyeZombieCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("플레이어만 이 명령어를 사용할 수 있습니다.");
            return false;
        }
        Player player = (Player) sender;
        BigEyeZombie bigEyeZombie = new BigEyeZombie(player.getLocation());

        return false;
    }
}
