package org.blog.paperedu.user.management.data.source;

import org.blog.paperedu.common.config.Config;
import org.blog.paperedu.user.management.data.entity.UserData;

import java.util.UUID;

public class UserDataConfig extends Config {
    public UserDataConfig(String basePath, String fileName) {
        super(basePath, fileName);
        loadDefaults();
    }

    public void storeUserData(UserData userData, UUID uuid){
        getConfig().set(uuid.toString(), userData);
        super.store();
    }

    public UserData loadUserData(UUID uuid){
        return (UserData) getConfig().get(uuid.toString());
    }

    public boolean hasUserData(UUID uuid){
        return getConfig().contains(uuid.toString());
    }

    @Override
    public void loadDefaults() {

    }

    @Override
    public void applySettings() {
        getConfig().options().copyDefaults(true);
    }
}
