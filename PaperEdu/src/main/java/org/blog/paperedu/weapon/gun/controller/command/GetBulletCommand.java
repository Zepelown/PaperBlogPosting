package org.blog.paperedu.weapon.gun.controller.command;

import org.blog.paperedu.weapon.gun.domain.model.gun.NormalPistol;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GetBulletCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage("플레이어만 이 명령어를 사용할 수 있습니다.");
                return false;
            }
            Player player = (Player) sender;
            NormalPistol normalPistol = new NormalPistol(NormalPistol.MAX_AMMO);
            normalPistol.giveBulletToPlayer(player);
        }

        return false;
    }
}
