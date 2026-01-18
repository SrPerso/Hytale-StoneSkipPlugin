package dev.perso_dev.stoneskip;

import org.bukkit.entity.Player;
import org.bukkit.entity.ThrowableProjectile;  // O Snowball/ThrownItem
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.NamespacedKey;
import org.bukkit.Material;

import java.util.Random;

public class StoneSkipListener implements Listener {
    private final StoneSkipPlugin plugin;
    private final NamespacedKey chargedKey = new NamespacedKey("stoneskip", "charged");
    private final NamespacedKey bounceKey = new NamespacedKey("stoneskip", "bounces");
    private final Random random = new Random();

    public StoneSkipListener(StoneSkipPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player)) return;
        ThrowableProjectile proj = (ThrowableProjectile) event.getEntity();

        if (!proj.getItemStack().getType().toString().toLowerCase().contains("rubble")) return;

        PersistentDataContainer data = proj.getPersistentDataContainer();
        Vector vel = proj.getVelocity();
        if (vel.length() > 1.0) {
            data.set(chargedKey, PersistentDataType.BYTE, (byte) 1);
        }
    }

    @EventHandler
    public void onHit(ProjectileHitEvent event) {
        ThrowableProjectile proj = (ThrowableProjectile) event.getEntity();
        PersistentDataContainer data = proj.getPersistentDataContainer();

        if (!data.has(chargedKey, PersistentDataType.BYTE) || data.get(chargedKey, PersistentDataType.BYTE) != 1) return;

        Block hit = event.getHitBlock();
        if (hit == null || !hit.getType().name().contains("WATER")) return;  // only water

        if (random.nextDouble() > 0.35) return;  // 35% chance

        event.setCancelled(true);


        double bounces = data.getOrDefault(bounceKey, PersistentDataType.DOUBLE, 0.0) + 1;
        if (bounces > 5) {
            proj.remove();
            return;
        }
        data.set(bounceKey, PersistentDataType.DOUBLE, bounces);


        Vector vel = proj.getVelocity();
        double speed = vel.length() * 0.8;
        Vector newVel = new Vector(vel.getX(), Math.abs(vel.getY()) * 0.6 + 0.1, vel.getZ())
                .normalize().multiply(speed);
        proj.setVelocity(newVel);

        plugin.getLogger().info("¡Boing! (" + bounces + ")");
    }
}