package org.blog.paperedu.user.management.view;

import org.blog.paperedu.user.management.domain.model.User;
import org.bukkit.entity.Player;

public class UserManagementView {
    public void sendUserInfo(Player sender, User userData){
        sender.sendMessage("----------------------------");
        sender.sendMessage(String.format("이름 : %s", userData.getDisplayName()));
        sender.sendMessage(String.format("직업 : %s", userData.getJob()));
        sender.sendMessage(String.format("랭크 : %s", userData.getRank()));
        sender.sendMessage(String.format("칭호 : %s", userData.getPrefix()));
        sender.sendMessage(String.format("소지 금액 : %s", userData.getMoney()));
        sender.sendMessage("----------------------------");
    }
}
