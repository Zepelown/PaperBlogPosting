package org.blog.paperedu.user.management.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.serialization.SerializableAs;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class User implements Comparable<User> {
    private UUID uuid;
    private String displayName;

    private String rank;

    private Double money;

    private String job;

    private String prefix;

    public User(String displayName){
        this.displayName = displayName;
    }

    public User(UUID uuid,String displayName, String rank, Double money, String job, String prefix) {
        this.uuid = uuid;
        this.displayName = displayName;
        this.rank = rank;
        this.money = money;
        this.job = job;
        this.prefix = prefix;
    }
    @Override
    public int compareTo(@NotNull User o) {
        return this.displayName.compareTo(o.displayName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(displayName, user.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(displayName);
    }
}
