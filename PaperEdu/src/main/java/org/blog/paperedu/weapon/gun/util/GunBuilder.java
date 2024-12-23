package org.blog.paperedu.weapon.gun.util;

import org.blog.paperedu.weapon.gun.domain.model.bullet.Bullet;
import org.blog.paperedu.weapon.gun.domain.model.gun.Gun;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;

public class GunBuilder {
    public static ItemStack buildGun(Material type, int amount, String displayName, String customItemTag, String... lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(Gun.TAG_KEY, PersistentDataType.STRING, customItemTag);
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        stack.setItemMeta(meta);
        return stack;
    }

    public static ItemStack buildBullet(Material type, int amount, String displayName, double damage, String... lore) {
        ItemStack stack = new ItemStack(type, amount);
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(Bullet.DAMAGE_TAG_KEY, PersistentDataType.DOUBLE, damage);
        meta.setDisplayName(displayName);
        meta.setLore(Arrays.asList(lore));
        stack.setItemMeta(meta);
        return stack;
    }

    public static boolean isGun(ItemStack item) {
        return item != null && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(Gun.TAG_KEY, PersistentDataType.STRING);
    }
}
