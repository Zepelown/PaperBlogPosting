package org.blog.paperedu.user.management.data.repository;

import org.blog.paperedu.PaperEdu;
import org.blog.paperedu.user.management.data.entity.UserData;
import org.blog.paperedu.user.management.domain.model.User;
import org.blog.paperedu.user.management.data.source.UserDataConfig;

import java.util.UUID;

public class UserDataRepository {

    private static final String CONFIG_FILE_NAME = "userdata.yml";
    private final String configBasePath;

    private final UserDataConfig userDataConfig;
    public UserDataRepository(){
        configBasePath = PaperEdu.getServerInstance().getDataFolder().getAbsolutePath();
        userDataConfig = new UserDataConfig(configBasePath,CONFIG_FILE_NAME);
    }

    public void reloadConfig(){
        userDataConfig.reload();
    }

    public void saveConfig(){
        userDataConfig.store();
    }

    public boolean hasUser(UUID uuid){
        return userDataConfig.hasUserData(uuid);
    }

    public void storeUserData(User user){
        UserData userData = new UserData(
                user.getDisplayName(),
                user.getRank(),
                user.getMoney(),
                user.getJob(),
                user.getPrefix()
        );
        userDataConfig.storeUserData(userData, user.getUuid());
    }

    public User loadUserData(UUID uuid){
        UserData userData = userDataConfig.loadUserData(uuid);
        User user = new User(
                uuid,
                userData.getDisplayName(),
                userData.getRank(),
                userData.getMoney(),
                userData.getJob(),
                userData.getPrefix()
        );
        return user;
    }


}
