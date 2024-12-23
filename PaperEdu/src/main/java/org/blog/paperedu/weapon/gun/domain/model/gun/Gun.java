package org.blog.paperedu.weapon.gun.domain.model.gun;

import org.blog.paperedu.PaperEdu;
import org.blog.paperedu.weapon.gun.common.GunSoundConstant;
import org.blog.paperedu.weapon.gun.domain.model.bullet.Bullet;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public interface Gun {
    NamespacedKey CURRENT_AMMO_TAG_KEY = new NamespacedKey(PaperEdu.getServerInstance(), "CURRENT_AMMO");
    NamespacedKey TAG_KEY = new NamespacedKey(PaperEdu.getServerInstance(), "GUN");
    Material DEFAULT_MATERIAL = Material.GOLDEN_HORSE_ARMOR;

    void fire(Player player, ItemStack gun);

    void giveGunToPlayer(Player player);

    void giveBulletToPlayer(Player player);

    void reload(Player player, ItemStack itemStack);

    void calculateReload(Player player, ItemStack gunItemStack, int bulletCount);

    default int countBullet(Player player, ItemStack itemStack) {
        int result = 0;
        for (ItemStack item : player.getInventory().getContents()) {
            if (item == null) {
                continue;
            }
            if (item.isSimilar(itemStack)) {
                result += item.getAmount();
            }
        }
        return result;
    }

    default void setCurrentAmmo(ItemStack gunItemStack, int currentAmmo) {
        ItemMeta itemMeta = gunItemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(CURRENT_AMMO_TAG_KEY, PersistentDataType.INTEGER, currentAmmo);
        gunItemStack.setItemMeta(itemMeta);
    }

    default void initReloadRunnable(Player player, Gun gun, ItemStack gunItemStack, int reloadTime, int maxAmmo, int bulletCount) {
        if (bulletCount < maxAmmo) {
            PaperEdu.getGunController().reload(player, gun, gunItemStack, bulletCount, reloadTime);
            return;
        }
        PaperEdu.getGunController().reload(player, gun, gunItemStack, maxAmmo, reloadTime);
    }

    default void throwBulletProjectile(Player player, int bulletSpeed, double damage) {
        Snowball projectile = player.launchProjectile(Snowball.class);
        projectile.getPersistentDataContainer().set(Bullet.DAMAGE_TAG_KEY, PersistentDataType.DOUBLE, damage);
        Vector multiplied = player.getEyeLocation().getDirection().multiply(bulletSpeed);
        projectile.setVelocity(multiplied);
        player.playSound(player.getLocation(), GunSoundConstant.FIRE_SOUND, GunSoundConstant.SOUND_VOLUME, GunSoundConstant.SOUND_PITCH);
    }
}
