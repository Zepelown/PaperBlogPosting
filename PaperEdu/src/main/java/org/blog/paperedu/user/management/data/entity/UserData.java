package org.blog.paperedu.user.management.data.entity;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("UserData")
@Getter
@Setter
public class UserData implements ConfigurationSerializable {
    private String displayName;

    private String rank;

    private Double money;

    private String job;

    private String prefix;
    public UserData(Map<String, Object> data){
        this.displayName = (String) data.get("displayName");
        this.rank = (String) data.get("rank");
        this.money = (Double) data.get("money");
        this.job = (String) data.get("job");
        this.prefix = (String) data.get("prefix");
    }

    public UserData(String displayName, String rank,Double money, String job, String prefix) {
        this.displayName = displayName;
        this.rank = rank;
        this.money = money;
        this.job = job;
        this.prefix = prefix;
    }

    @Override
    public @NotNull Map<String, Object> serialize() {
        HashMap<String, Object> mapSerializer = new HashMap<>();
        mapSerializer.put("displayName", this.displayName);
        mapSerializer.put("rank", this.rank);
        mapSerializer.put("money", this.money);
        mapSerializer.put("job", this.job);
        mapSerializer.put("prefix",this.prefix);
        return mapSerializer;
    }

}
