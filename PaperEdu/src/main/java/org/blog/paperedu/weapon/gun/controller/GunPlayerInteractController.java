package org.blog.paperedu.weapon.gun.controller;

import org.blog.paperedu.weapon.gun.domain.model.bullet.Bullet;
import org.blog.paperedu.weapon.gun.domain.model.gun.Gun;
import org.blog.paperedu.weapon.gun.domain.model.gun.NormalPistol;
import org.blog.paperedu.weapon.gun.util.GunBuilder;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class GunPlayerInteractController implements Listener {
    @EventHandler
    public void interactGun(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if ((event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR)
                || event.getItem() == null) {
            return;
        }

        if (GunBuilder.isGun(event.getPlayer().getInventory().getItemInMainHand())) {
            PersistentDataContainer persistentDataContainer = event.getItem().getItemMeta().getPersistentDataContainer();
            Gun gun;
            switch (persistentDataContainer.get(Gun.TAG_KEY, PersistentDataType.STRING)) {
                case NormalPistol.GUN_TAG:
                    gun = new NormalPistol(persistentDataContainer.getOrDefault(NormalPistol.CURRENT_AMMO_TAG_KEY, PersistentDataType.INTEGER, NormalPistol.MAX_AMMO));
                    gun.fire(player, event.getPlayer().getInventory().getItemInMainHand());
                    break;
                default:
                    gun = new NormalPistol(persistentDataContainer.getOrDefault(NormalPistol.CURRENT_AMMO_TAG_KEY, PersistentDataType.INTEGER, NormalPistol.MAX_AMMO));
                    gun.fire(player, event.getPlayer().getInventory().getItemInMainHand());
            }
        }
    }

    @EventHandler
    public void onProjectileDamaged(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Snowball && !e.getDamager().getPersistentDataContainer().isEmpty()) {
            e.setDamage(e.getDamager().getPersistentDataContainer().getOrDefault(Bullet.DAMAGE_TAG_KEY, PersistentDataType.DOUBLE, 0.5));
        }
    }
}
