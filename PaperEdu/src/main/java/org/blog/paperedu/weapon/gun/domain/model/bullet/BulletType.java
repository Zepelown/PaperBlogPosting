package org.blog.paperedu.weapon.gun.domain.model.bullet;

public enum BulletType {
    PISTOL(3);

    private int speed;

    BulletType(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
