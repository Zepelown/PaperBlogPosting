package org.blog.paperedu.mob.domain.model;

import org.blog.paperedu.PaperEdu;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class BigEyeZombie {
    public static NamespacedKey BIG_EYE_ZOMBIE = new NamespacedKey(PaperEdu.getServerInstance(),"BIG_EYE_ZOMBIE");
    public BigEyeZombie(Location location) {
        Zombie zombie = (Zombie) location.getWorld().spawnEntity(location, EntityType.ZOMBIE);

        zombie.setCustomName("맑눈광 좀비");
        zombie.setCustomNameVisible(true);
        zombie.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_AXE, 1));
        zombie.getEquipment().setItem(EquipmentSlot.CHEST, new ItemStack(Material.DIAMOND_CHESTPLATE, 1));
        zombie.getEquipment().setItem(EquipmentSlot.FEET, new ItemStack(Material.DIAMOND_BOOTS, 1));
        zombie.getEquipment().setItem(EquipmentSlot.HEAD, new ItemStack(Material.DIAMOND_HELMET, 1));
        zombie.getEquipment().setItem(EquipmentSlot.LEGS, new ItemStack(Material.DIAMOND_LEGGINGS, 1));

        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(100);
        zombie.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
        zombie.setHealth(100);

        zombie.getPersistentDataContainer().set(BIG_EYE_ZOMBIE, PersistentDataType.BOOLEAN, true);
    }
}
