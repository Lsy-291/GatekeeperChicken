package lsy291.gatekeeperchicken.listeners;

import lsy291.gatekeeperchicken.utils.PlayerIdentityStatsAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import static lsy291.gatekeeperchicken.config.ConfigItems.autoLoginTime;

public class PlayerQuitListener implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (autoLoginTime <= 0) PlayerIdentityStatsAPI.removePlayerLoginState(player.getUniqueId());
        if (PlayerIdentityStatsAPI.isPlayerLoggingIn(player.getUniqueId())) PlayerIdentityStatsAPI.removePlayerLoginTimeOut(player.getUniqueId());
    }
}
