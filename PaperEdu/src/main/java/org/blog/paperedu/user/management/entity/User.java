package org.blog.paperedu.user.management.entity;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;

public class User implements Comparable<User> {
    private UUID uuid;
    private String displayName;

    private String rank;

    private Long money;

    private String job;

    private String prefix;

    public User(String displayName){
        this.displayName = displayName;
    }

    public User(UUID uuid,   String displayName, String rank, Long money, String job, String prefix) {
        this.uuid = uuid;
        this.displayName = displayName;
        this.rank = rank;
        this.money = money;
        this.job = job;
        this.prefix = prefix;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
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
