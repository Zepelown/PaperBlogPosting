package org.blog.paperedu.weapon.gun.domain.model.task;

import org.blog.paperedu.weapon.gun.common.GunSoundConstant;
import org.blog.paperedu.weapon.gun.domain.model.gun.Gun;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

public class ReloadScheduler {
    private final static ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
    private final Map<UUID, Boolean> reloadingPlayers = new ConcurrentHashMap<>();

    public void reload(Player player, Gun gun, ItemStack gunItemStack, int bulletCount, int reloadTime) {
        Executor delayedExecutor = CompletableFuture.delayedExecutor(reloadTime, TimeUnit.SECONDS, scheduledExecutorService);

        synchronized (player.getUniqueId()) {
            if (reloadingPlayers.containsKey(player.getUniqueId()) && reloadingPlayers.get(player.getUniqueId())) {
                player.sendMessage("이미 장전 중입니다.");
                return;
            }
            reloadingPlayers.put(player.getUniqueId(), true);
            player.sendMessage("장전 시작합니다.");


            CompletableFuture.supplyAsync(() -> {
                try {
                    gun.calculateReload(player, gunItemStack, bulletCount);
                    return "Reload Completed";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Reload Failed";
                }
            }, delayedExecutor).thenAccept(result -> {
                player.sendMessage("장전이 완료되었습니다.");
                player.playSound(player.getLocation(), GunSoundConstant.RELOADING_SOUND, GunSoundConstant.SOUND_VOLUME, GunSoundConstant.SOUND_PITCH);
                synchronized (player.getUniqueId()) {
                    reloadingPlayers.remove(player.getUniqueId());
                }
            }).exceptionally(e -> {
                e.printStackTrace();
                synchronized (player.getUniqueId()) {
                    reloadingPlayers.remove(player.getUniqueId());
                }
                return null;
            });
        }
    }
}
