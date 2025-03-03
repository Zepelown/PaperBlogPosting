package org.blog.paperedu.weapon.gun.domain.model.gun;

import org.blog.paperedu.weapon.gun.common.GunSoundConstant;
import org.blog.paperedu.weapon.gun.domain.model.bullet.Bullet;
import org.blog.paperedu.weapon.gun.domain.model.bullet.BulletType;
import org.blog.paperedu.weapon.gun.util.GunBuilder;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class NormalPistol implements Gun {
    public static final String GUN_TAG = "NORMAL_PISTOL";
    public static final int MAX_AMMO = 7;
    public static final int RELOAD_TIME = 2;
    public static final double DAMAGE = 10.0;
    private static final int customModelData = 1;
    private final Bullet normalPistolBullet;
    private int currentAmmo;

    public NormalPistol(int currentAmmo) {
        this.currentAmmo = currentAmmo;

        ItemStack bulletItem = GunBuilder.buildBullet(
                Bullet.DEFAULT_MATERIAL,
                1,
                "딱총 총알",
                DAMAGE,
                "딱총 총알이다"
        );

        normalPistolBullet = new Bullet(BulletType.PISTOL, bulletItem);
    }

    @Override
    public void fire(Player player, ItemStack gunItemStack) {
        if (currentAmmo <= 0) {
            player.playSound(player.getLocation(), GunSoundConstant.NOT_EXIST_AMMO_SOUND, GunSoundConstant.SOUND_VOLUME, GunSoundConstant.SOUND_PITCH);
            reload(player, gunItemStack);
            return;
        }

        throwBulletProjectile(player, normalPistolBullet.getType().getSpeed(), DAMAGE);
        setCurrentAmmo(gunItemStack, --currentAmmo);
        player.sendMessage("현재 남은 총알 :" + currentAmmo);
    }

    @Override
    public void giveGunToPlayer(Player player) {
        ItemStack itemStack = GunBuilder.buildGun(
                Gun.DEFAULT_MATERIAL,
                1,
                "딱총",
                GUN_TAG,
                customModelData,
                "평범한 딱총이다"
        );
        itemStack.getItemMeta().getPersistentDataContainer().set(CURRENT_AMMO_TAG_KEY, PersistentDataType.INTEGER, MAX_AMMO);
        player.getInventory().addItem(itemStack);
    }

    @Override
    public void giveBulletToPlayer(Player player) {
        for (int i = 0; i < 32; i++) {
            player.getInventory().addItem(normalPistolBullet.getItemStack());
        }
    }

    @Override
    public void reload(Player player, ItemStack gunItemStack) {
        int bulletCount = countBullet(player, normalPistolBullet.getItemStack());
        if (bulletCount <= 0) {
            player.sendMessage("재장전할 총알이 부족합니다.");
            player.playSound(player.getLocation(), GunSoundConstant.NOT_EXIST_AMMO_SOUND, GunSoundConstant.SOUND_VOLUME, GunSoundConstant.SOUND_PITCH);
            return;
        }
        initReloadRunnable(player, this, gunItemStack, RELOAD_TIME, MAX_AMMO, bulletCount);
    }

    @Override
    public void calculateReload(Player player, ItemStack gunItemStack, int bulletCount) {
        ItemStack bulletStack = normalPistolBullet.getItemStack().clone();
        bulletStack.setAmount(bulletCount);
        player.getInventory().removeItem(bulletStack);
        setCurrentAmmo(gunItemStack, Math.min(bulletCount, MAX_AMMO));
    }


}
