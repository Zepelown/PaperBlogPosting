package org.blog.paperedu.mob.controller;

import org.blog.paperedu.mob.domain.model.BigEyeZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataType;

public class MobHitController implements Listener {
    @EventHandler
    public void customMobHitEvent(EntityDamageByEntityEvent e) {
        if (!e.getEntity().getPersistentDataContainer().has(BigEyeZombie.BIG_EYE_ZOMBIE, PersistentDataType.BOOLEAN)) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getDamager();
        Zombie zombie = (Zombie) e.getEntity();
        player.sendMessage("맑눈광 좀비 남은 체력 : " + (int)zombie.getHealth());
    }
}
