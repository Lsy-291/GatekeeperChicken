package lsy291.gatekeeperchicken.listeners;

import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerActionsListener implements Listener {
    @EventHandler
    public void onEntityTarget(EntityTargetEvent event) {
        Entity target = event.getTarget();
        if (target instanceof Player && !PlayerIdentityStatsAPI.isPlayerLoggedIn(((Player) target).getUniqueId())) {
            // prevent creatures from developing hostility towards players.
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        // Prohibit players from moving
        if (!PlayerIdentityStatsAPI.isPlayerLoggedIn(player.getUniqueId())) { event.setCancelled(true); }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity victim = event.getEntity();
        // Prevent player injury
        if (victim instanceof Player && !PlayerIdentityStatsAPI.isPlayerLoggedIn(((Player) victim).getUniqueId())) { event.setCancelled(true); }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (!PlayerIdentityStatsAPI.isPlayerLoggedIn(event.getPlayer().getUniqueId())) {
            // prevent players from picking up items.
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (!PlayerIdentityStatsAPI.isPlayerLoggedIn(event.getPlayer().getUniqueId())) {
            // prevent block destruction.
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!PlayerIdentityStatsAPI.isPlayerLoggedIn(event.getPlayer().getUniqueId())) {
            // prevent block placement
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if (damager instanceof Player && !PlayerIdentityStatsAPI.isPlayerLoggedIn(((Player) damager).getUniqueId())) {
            // prevent players from causing any damage
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (event.getEntity() instanceof Player && !PlayerIdentityStatsAPI.isPlayerLoggedIn(((Player) event.getEntity()).getUniqueId())) {
            // Prevent players from getting hungry
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!PlayerIdentityStatsAPI.isPlayerLoggedIn(event.getPlayer().getUniqueId())) {
            // Prohibit player right click interaction events
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!PlayerIdentityStatsAPI.isPlayerLoggedIn(event.getPlayer().getUniqueId())) {
            // Prohibit players from discarding items
            event.setCancelled(true);
        }
    }
}
