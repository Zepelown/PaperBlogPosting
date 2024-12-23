package org.blog.paperedu.weapon.gun.domain.model.bullet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.blog.paperedu.PaperEdu;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public class Bullet {
    public static final NamespacedKey DAMAGE_TAG_KEY = new NamespacedKey(PaperEdu.getServerInstance(), "BULLET_DAMAGE");
    public static final Material DEFAULT_MATERIAL = Material.CLOCK;
    private BulletType type;
    private ItemStack itemStack;
}
